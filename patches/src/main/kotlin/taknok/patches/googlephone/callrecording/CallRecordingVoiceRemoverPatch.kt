package taknok.patches.googlephone.callrecording

import app.morphe.patcher.patch.ResourcePatchContext
import app.morphe.patcher.patch.resourcePatch
import org.w3c.dom.Element
import taknok.patches.shared.Constants.COMPATIBILITY_DIALER
import java.io.FileNotFoundException
import java.nio.file.Files
import java.util.logging.Logger
import kotlin.io.path.isDirectory
import kotlin.io.path.name

@Suppress("unused")
val callRecordingVoiceRemoverPatch = resourcePatch(
    name = "Call recording announcements remover",
    description = "Removes the announcements when starting or stopping a call recording.",
) {
    compatibleWith(COMPATIBILITY_DIALER)

    execute {
        listOf(
            "call_recording_starting_voice",
            "call_recording_ending_voice",
        ).forEach { stringName ->
            editStringAllResources(this, stringName, " ")
        }

        editStringAllResources(this, "search_bar_hint", ".", Operation.APPEND)
        Logger.getLogger(javaClass.name)
            .info("Adding '.' to search hint to visually detect patched app")
    }
}

private enum class Operation { SUBSTITUTE, APPEND }

private fun editStringResources(
    context: ResourcePatchContext,
    resource: String,
    stringName: String,
    stringValue: String,
    operation: Operation = Operation.SUBSTITUTE,
) {
    try {
        context.document(resource).use { document ->
            val nodes = document.getElementsByTagName("string")
            val stringNode = (0 until nodes.length)
                .asSequence()
                .mapNotNull { nodes.item(it) as? Element }
                .firstOrNull { it.getAttribute("name") == stringName }

            when (operation) {
                Operation.SUBSTITUTE -> stringNode?.textContent = stringValue
                Operation.APPEND -> stringNode?.textContent =
                    stringNode.textContent.orEmpty() + stringValue
            }
        }
    } catch (_: FileNotFoundException) {
        // Some locales do not define every string.
    }
}

private fun editStringAllResources(
    context: ResourcePatchContext,
    stringName: String,
    stringValue: String,
    operation: Operation = Operation.SUBSTITUTE,
) {
    editStringResources(context, "res/values/strings.xml", stringName, stringValue, operation)

    val resources = context["res"].toPath()
    Files.walk(resources, 1).use { paths ->
        paths
            .filter { it.isDirectory() && it.name.startsWith("values-") }
            .filter { Files.exists(it.resolve("strings.xml")) }
            .forEach { dir ->
                editStringResources(
                    context,
                    "res/${dir.name}/strings.xml",
                    stringName,
                    stringValue,
                    operation,
                )
            }
    }
}
