package by.serzh

import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFRun
import java.io.File
import java.io.FileOutputStream

class Generator {

    fun generate(templatePath: String, outPath: String, headings: List<String>, datum: List<String>) {
        val doc = File(templatePath).inputStream()
        val document = XWPFDocument(doc)
        val paragraphs = document.paragraphs
        paragraphs.stream()
                .flatMap { par -> par.runs.stream() }
                .filter { run -> run.getText(0) != null }
                .forEach { run -> processRun(run, headings, datum) }
        document.tables.stream()
                .flatMap { table -> table.rows.stream() }
                .flatMap { row -> row.tableCells.stream() }
                .flatMap { cell -> cell.paragraphs.stream() }
                .flatMap { par -> par.runs.stream() }
                .filter { run -> run.getText(0) != null }
                .forEach { run -> processRun(run, headings, datum) }
        document.write(FileOutputStream(outPath))
    }

    fun processRun(run: XWPFRun, heading: List<String>, datum: List<String>) {
        val text = run.getText(0)
        heading.indices
                .filter { text.contains(heading[it]) }
                .map { text.replace(heading[it], datum[it]) }
                .forEach { run.setText(it, 0) }
    }
}