package agami.kotlin.agamicovid19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    lateinit var listAdapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            val response= withContext(Dispatchers.IO){Client.api.execute()}
            if(response.isSuccessful){
                val data= Gson().fromJson(response.body?.string(),Response::class.java)
                launch(Dispatchers.Main) {
                    bindCombineData(data.statewise[0])
                    bindStateWiseData(data.statewise.subList(0,data.statewise.size))
                }
            }
        }

    }
    private fun bindStateWiseData(subList: List<StatewiseItem>){
        listAdapter= ListAdapter(subList)
        list.addHeaderView(LayoutInflater.from(this).inflate(R.layout.list_header,list,false))
        list.adapter=listAdapter
    }
    private fun bindCombineData(data: StatewiseItem)
    {
        confirmedTv.text=data.confirmed
        activeTv.text=data.active
        recoverTv.text=data.recovered
        deceasedTv.text=data.deaths
    }
}
