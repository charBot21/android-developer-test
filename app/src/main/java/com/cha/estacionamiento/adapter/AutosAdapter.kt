package com.cha.estacionamiento.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cha.estacionamiento.R
import com.cha.estacionamiento.data.local.entity.AddAutos
import com.cha.estacionamiento.model.interfaces.HomeListener

class AutosAdapter( var autosList: List<AddAutos>, private var clickItem: HomeListener):
    RecyclerView.Adapter<AutosAdapter.AutosViewHolder>() {

    class AutosViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var tvPlaca       : TextView = view.findViewById(R.id.tv_placa)
        var tvTipoAuto    : TextView = view.findViewById(R.id.tv_tipo_auto)
        var tvHoraIngreso : TextView = view.findViewById(R.id.tv_hora)
        var tvTarifa      : TextView = view.findViewById(R.id.tv_tarifa)
        var tvStatus      : TextView = view.findViewById(R.id.tv_status)

        fun initialize( item: AddAutos, action: HomeListener ) {
            tvPlaca.text       = item.placa
            tvTipoAuto.text    = item.tipoAuto
            tvHoraIngreso.text = item.horaIngreso
            tvTarifa.text      = item.tarifaAuto.toString()
            tvStatus.text      = item.statusAuto

            itemView.setOnClickListener {
                action.autoClicked(item, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AutosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AutosViewHolder(inflater.inflate(R.layout.recyclerview_autos, parent, false))
    }

    override fun onBindViewHolder(holder: AutosViewHolder, position: Int) {
        holder.initialize(autosList[position], clickItem)
    }

    override fun getItemCount(): Int {
        return autosList.size
    }
}