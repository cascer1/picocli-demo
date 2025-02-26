package com.iodigital.javachapter.picoclidemo

import com.iodigital.javachapter.picoclidemo.commands.FakeTarCommand
import com.iodigital.javachapter.picoclidemo.commands.HelloWorldCommand
import picocli.CommandLine
import picocli.CommandLine.Model.CommandSpec
import picocli.CommandLine.ParameterException
import picocli.CommandLine.Spec
import java.util.concurrent.Callable
import kotlin.system.exitProcess


@CommandLine.Command(
    subcommands = [HelloWorldCommand::class, FakeTarCommand::class]
)
class Demo : Callable<Int> {
    @Throws(Exception::class)
    override fun call(): Int {
        throw ParameterException(spec.commandLine(), "Missing required subcommand")
    }

    @Spec
    lateinit var spec: CommandSpec

    @CommandLine.Option(names = ["-h", "--help"], usageHelp = true, description = ["display this help message"])
    var usageHelpRequested: Boolean = false

    companion object {
        @JvmStatic
        public final fun main(args: Array<String>) {
            val app: Demo = CommandLine.populateCommand(Demo(), *args)
            if (app.usageHelpRequested) {
                CommandLine.usage(Demo(), System.out)
                return
            }

            val returnCode = CommandLine(Demo())
                .setExecutionExceptionHandler(PrintExceptionMessageHandler())
                .execute(*args)

            exitProcess(returnCode)
        }
    }
}