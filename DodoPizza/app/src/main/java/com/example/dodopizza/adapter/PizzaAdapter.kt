package com.example.dodopizza.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dodopizza.databinding.ActivityMainBinding
import com.example.dodopizza.databinding.ItemPizzaBinding
import com.example.dodopizza.model.Pizza
import com.example.dodopizza.R
import com.example.dodopizza.databinding.ItemComboBinding
import com.example.dodopizza.model.PizzaType

class PizzaAdapter(
    private val onPizzaClick: (Pizza) -> Unit,

    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private val pizzaList: ArrayList<Pizza> = ArrayList()

    companion object {
        private const val TYPE_REGULAR = 0
        private const val TYPE_COMBO = 1
    }


    fun setData(pizzas: ArrayList<Pizza>) {
        pizzaList.clear()
        pizzaList.addAll(pizzas)

        /**
         * метод для обновления списка элементов
         */

        notifyDataSetChanged()

    }

    override fun getItemViewType(position: Int): Int {
        return when (pizzaList[position].type) {
            PizzaType.REGULAR -> {
                TYPE_REGULAR
            }
            PizzaType.COMBO -> {
                TYPE_COMBO
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_COMBO -> {
                val binding = ItemComboBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ComboViewHolder(binding)
            }
            else -> {
                val binding = ItemPizzaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                RegularViewHolder(binding)
            }
        }
    }

    /**
     * метод который будет создавать view для каждого объекта
     * */
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder(
//            ItemPizzaBinding.inflate(
//                LayoutInflater.from(parent.context), parent, false
//            )
//        )
//    }


    /**
     * метод для определения количество элементов списка
     * */
    override fun getItemCount() = pizzaList.size

    /**
     * для вызова метода из ViewHolder`s
     * */
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(pizzaList[position])
//    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RegularViewHolder -> holder.bind(pizzaList[position])
            is ComboViewHolder -> holder.bind(pizzaList[position])
        }
    }



//    inner class ViewHolder(
//        private val binding: ItemPizzaBinding
//    ) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(pizza: Pizza) {
//            with(binding) {
//                mainImage.setImageResource(pizza.images)
//                title.text = pizza.title
//                description.text = pizza.description
//                textPrice.text = root.context.getString(R.string.text_format, pizza.price)
//
//                root.setOnClickListener() {
//                    onPizzaClick(pizza)
//                }
//
//            }
//        }
//    }

    inner class RegularViewHolder(private val binding: ItemPizzaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pizza: Pizza) {
            with(binding) {
                mainImage.setImageResource(pizza.images)
                title.text = pizza.title
                description.text = pizza.description
                textPrice.text = root.context.getString(R.string.text_format, pizza.price)

                root.setOnClickListener() {
                    onPizzaClick(pizza)
                }

            }
        }
    }

    // ViewHolder для комбо предложения
    inner class ComboViewHolder(private val binding: ItemComboBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pizza: Pizza) {
            with(binding){
                comboImage.setImageResource(pizza.images)
                comboTitle.text = pizza.title
                comboDescription.text = pizza.description
                comboPrice.text = root.context.getString(R.string.text_format, pizza.price)
            }
        }
    }
}
