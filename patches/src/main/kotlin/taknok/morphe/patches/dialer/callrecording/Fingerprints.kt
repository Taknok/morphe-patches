/*
 * Fingerprints for the Google Phone call-recording implementation.
 *
 * The APK currently obfuscates the containing class as `defpackage.lal` and
 * the method as `b`. Those names are intentionally not part of this
 * fingerprint.
 */
package taknok.patches.googlephone.callrecording

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.string
import app.morphe.patcher.opcode
import com.android.tools.smali.dexlib2.Opcode

/**
 * Matches the method that calculates whether the manual call-recording
 * action is available for the current call.
 *
 * Current dex signature: lal.b(Ljava/lang/Object;)Lakjr;.
 * JADX renders the input as a List after the cast, but the compiler-generated
 * bridge method receives java.lang.Object at bytecode level.
 *
 * The filters identify the stable diagnostic string in the `kxa.a() == false`
 * branch and a following goto. No obfuscated return/class descriptor is used.
 */
object CallRecordingAvailabilityFingerprint : Fingerprint(
    parameters = listOf("Ljava/lang/Object;"),
    filters = listOf(
        string("recording not available: canRecord returned false"),
        opcode(Opcode.GOTO),
    ),
)
