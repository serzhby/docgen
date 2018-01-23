package by.serzh

import java.io.InputStream
import java.util.*

class DataReader {

    fun read(inputStream: InputStream, delimeter: String): CsvData {
        val result = CsvData()
        val allLines = ArrayList<String>()
        inputStream.bufferedReader().useLines { lines ->
            lines.forEach {
                allLines.add(it)
            }
        }
        for (i in allLines.indices) {
            if (i == 0) {
                val heading = allLines[0].split(delimeter)
                result.setHeading(heading)
            } else {
                val line = allLines[i]
                val parts = line.split(delimeter)
                val lineItems = ArrayList<String>()
                for (j in result.heading.indices) {
                    if (j >= parts.size) {
                        lineItems.add("")
                    } else {
                        lineItems.add(parts[j])
                    }
                }
                result.addLine(lineItems)
            }
        }
        return result
    }
}