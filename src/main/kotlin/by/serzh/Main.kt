package by.serzh

import java.io.File

fun main(args: Array<String>) {
    if (args.size < 3) {
        println("Usage: <templateFile> <inputFile> <outputDir>")
        System.exit(1)
    }
    val templateFile = args[0]
    val inputFile = args[1]
    val outputDir = args[2]
    val generator = Generator()
    val inputStream = File(inputFile).inputStream()
    val data = DataReader().read(inputStream, ",")
    for (i in data.data.indices) {
        generator.generate(templateFile, "$outputDir/$i.docx", data.heading, data.data[i])
    }
}