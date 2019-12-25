package io.github.mohamedisoliman.softxpert.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.github.mohamedisoliman.softxpert.R
import io.github.mohamedisoliman.softxpert.data.entities.Car
import kotlinx.android.synthetic.main.item_car.view.*

/**
 *
 * Created by Mohamed Ibrahim on 2019-12-25.
 */
class CarsAdapter : RecyclerView.Adapter<CarViewHolder>() {

    private val cars = mutableListOf<Car>()

    fun showData(cars: List<Car>) {
        this.cars.clear()
        this.cars.addAll(cars)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false)
        return CarViewHolder(view)

    }

    override fun getItemCount(): Int = cars.size

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(cars[position])
    }


}

class CarViewHolder(item: View) : RecyclerView.ViewHolder(item) {

    fun bind(car: Car) {
        itemView.brand.text = "Brand: ${car.brand}"
        itemView.year.text = "Construction year: ${car.constructionYear ?: "NA"}"
        itemView.status.text = "Status: ${if (car.isUsed == true) "Used" else "New"}"
        Picasso.get().isLoggingEnabled = true
        Picasso.get().load(car.imageUrl)
            .placeholder(R.drawable.ic_directions_car_black_24dp)
            .into(itemView.carImage)
    }
}