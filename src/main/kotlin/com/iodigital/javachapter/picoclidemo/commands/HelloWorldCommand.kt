package com.iodigital.javachapter.picoclidemo.commands

import picocli.CommandLine
import java.util.concurrent.Callable

@CommandLine.Command(
    name = "hello",
    description = ["Say hello"],
    headerHeading = "@|bold,underline Usage|@:%n%n",
    descriptionHeading = "%n@|bold,underline Description|@:%n%n",
    parameterListHeading = "%n@|bold,underline Parameters|@:%n",
    optionListHeading = "%n@|bold,underline Options|@:%n"
)
class HelloWorldCommand: Callable<Int> {

    override fun call(): Int {
        println("Hello World!")
        return 0
    }
}