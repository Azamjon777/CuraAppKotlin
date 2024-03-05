package com.azamjon.cursapikotlin

import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private var doc: Document? = null
    private var secThread: Thread? = null
    private var runnable: Runnable? = null
    private var listView: ListView? = null
    private var adapter: CustomArrayAdapter? = null
    private var arrayList: MutableList<ListItemClass>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        listView = findViewById(R.id.listView)
        arrayList = ArrayList()
        adapter = CustomArrayAdapter(
            this, R.layout.list_item_1,
            arrayList as ArrayList<ListItemClass>, layoutInflater
        )
        this.listView?.adapter = adapter
        runnable = Runnable { web() }
        secThread = Thread(runnable)
        secThread!!.start()
    }

    private fun web() {
        try {
            doc = Jsoup.connect("https://minfin.com.ua/currency/").get()
            val tables: Elements = doc!!.getElementsByTag("tbody")
            val ourTable: Element = tables[0]
            val elementsFromTable: Elements = ourTable.children()
            val dollar: Element = elementsFromTable[0]
            val dollarElements: Elements = dollar.children()
            Log.d("work", dollar.text())
            var i = 0
            while (i < ourTable.childrenSize()) { // Corrected the condition
                val items = ListItemClass()
                items.data1 = ourTable.children()[i].child(0).text()
                items.data2 = ourTable.children()[i].child(1).text()
                items.data3 = ourTable.children()[i].child(2).text()
                items.data4 = ourTable.children()[i].child(3).text()
                arrayList!!.add(items)
                i++
            }
            runOnUiThread { adapter?.notifyDataSetChanged() }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}