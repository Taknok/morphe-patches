package taknok.patches.com.google.android.dialer.callrecording

import app.morphe.patcher.extensions.InstructionExtensions.addInstruction
import app.morphe.patcher.patch.bytecodePatch
import taknok.patches.com.google.android.dialer.shared.Constants.COMPATIBILITY_DIALER
/**
 * Makes the manual recording action available when the recorder capability
 * gate is the only rejected condition.
 *
 * Existing emergency, conference, CDMA, video, RTT, Fi, and missing-feature
 * exits remain unchanged because they jump to the common false return before
 * this insertion point.
 */
val enableCallRecordingPatch = bytecodePatch(
    name = "Enable call recording",
    description = "Enables the in-call recording action when the device recorder capability is unavailable.",
    default = true,
) {
    compatibleWith(COMPATIBILITY_DIALER)
    execute {
        CallRecordingAvailabilityFingerprint.let {
            val exitIndex = it.instructionMatches[1].index
            it.method.addInstruction(exitIndex, "const/4 v0, 0x1")
        }
    }
}
