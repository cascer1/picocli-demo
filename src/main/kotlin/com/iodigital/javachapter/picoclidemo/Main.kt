package com.iodigital.javachapter.picoclidemo

import com.iodigital.javachapter.picoclidemo.commands.HelloWorldCommand
import picocli.CommandLine
import java.time.Duration
import java.time.Instant
import java.util.concurrent.Callable

@CommandLine.Command(
    subcommands = [HelloWorldCommand::class],
)
class Main : Callable<Int> {
    @Throws(Exception::class)
    override fun call(): Int {
        return 0
    }

    companion object {
        @JvmStatic
        public final fun main(args: Array<String>) {
            val returnCode = CommandLine(Main())
                .setExecutionExceptionHandler(PrintExceptionMessageHandler())
                .execute(*args)

            println("DONE")

            System.exit(returnCode)
        }
    }
}