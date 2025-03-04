package com.iodigital.javachapter.picoclidemo

import picocli.CommandLine
import picocli.CommandLine.Help.Ansi
import picocli.CommandLine.IParameterExceptionHandler


class PrettyParameterExceptionHandler : IParameterExceptionHandler {
    override fun handleParseException(ex: CommandLine.ParameterException, args: Array<out String>?): Int {
        // Print error in red (if terminal supports it)
        System.err.println(Ansi.AUTO.string("@|bold,red ERROR:|@ " + ex.message))
        System.err.println()

        // Print usage help
        ex.commandLine.usage(System.err, Ansi.AUTO)

        return 1 // Exit code
    }

}