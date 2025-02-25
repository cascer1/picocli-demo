package com.iodigital.javachapter.picoclidemo

import picocli.CommandLine
import picocli.CommandLine.IExecutionExceptionHandler

class PrintExceptionMessageHandler : IExecutionExceptionHandler {
    override fun handleExecutionException(ex: Exception, cmd: CommandLine, parseResult: CommandLine.ParseResult): Int {
        // bold red error message

        cmd.err.println(cmd.colorScheme.errorText(ex.message))

        return if (cmd.exitCodeExceptionMapper != null) cmd.exitCodeExceptionMapper.getExitCode(ex) else cmd.commandSpec
            .exitCodeOnExecutionException()
    }
}