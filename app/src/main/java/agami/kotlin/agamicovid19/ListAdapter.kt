package agami.kotlin.agamicovid19

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_main.view.activeTv
import kotlinx.android.synthetic.main.activity_main.view.confirmedTv
import kotlinx.android.synthetic.main.activity_main.view.deceasedTv
import kotlinx.android.synthetic.main.activity_main.view.recoverTv
import kotlinx.android.synthetic.main.item_list.view.*

class ListAdapter(val list: List <StatewiseItem>): BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view =convertView?: LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_list,parent,false)
        val item=list[position]
        view.confirmedTv.text=item.confirmed
        view.activeTv.text=item.active
        view.recoverTv.text=item.recovered
        view.deceasedTv.text=item.deaths
        view.stateTv.text=item.state
        return view
    }

    override fun getItem(position: Int)= list[position]
    override fun getItemId(position: Int)= position.toLong()
    override fun getCount(): Int =list.size
}