package com.iodigital.javachapter.picoclidemo.commands

import picocli.CommandLine.*
import java.util.concurrent.Callable

@Command(
    name = "tar",
    description = ["A command with many options"]
)
class FakeTarCommand : Callable<Int> {

    @ArgGroup(
        exclusive = true,
        multiplicity = "1",
        heading = "Operation Mode\n"
    )
    lateinit var operationMode: OperationModeGroup

    @ArgGroup(
        exclusive = true,
        multiplicity = "1",
        heading = "Compression Mode\n"
    )
    lateinit var compressionMode: CompressionModeGroup

    @Option(names = ["-h", "--help"], usageHelp = true, description = ["display this help message"])
    var usageHelpRequested: Boolean = false

    @Option(
        names = ["-f", "--file"],
        description = ["The name of the archive file"],
        required = true
    )
    lateinit var fileName: String

    @Parameters(
        arity = "1.."
    )
    lateinit var files: Array<String>

    class OperationModeGroup {
        @Option(
            names = ["-x", "--extract"],
            description = ["Extract an archive"],
            required = true
        )
        var extract = false

        @Option(
            names = ["-c", "--create"],
            description = ["Create an archive"],
            required = true
        )
        var create = false

        override fun toString(): String {
            if (extract) {
                return "extract"
            }

            if (create) {
                return "create"
            }

            return "error"
        }
    }

    class CompressionModeGroup {
        @Option(
            names = ["-z", "--gzip"],
            description = ["Use gzip"]
        )
        var gzip = false

        @Option(
            names = ["-j"],
            description = ["Use bzip"]
        )
        var bzip = false

        @Option(
            names = ["-J", "--xz"],
            description = ["Use xz"]
        )
        var xz = false

        @Option(
            names = ["-a"],
            description = ["Automatically determine compression method"]
        )
        var automatic = false

        override fun toString(): String {
            if (gzip) return "gzip"
            if (bzip) return "bzip"
            if (xz) return "xz"
            if (automatic) return "automatic"

            return "error"
        }
    }

    override fun call(): Int {
        println(toString())
        return 0
    }

    override fun toString(): String {
        return """
            Filename: $fileName
            Operation mode: $operationMode
            Compression mode:  $compressionMode
            Files: ${files.joinToString(", ")}
        """.trimIndent()
    }
}