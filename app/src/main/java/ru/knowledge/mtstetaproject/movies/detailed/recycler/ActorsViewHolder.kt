package ru.knowledge.mtstetaproject.movies.detailed.recycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.knowledge.mtstetaproject.R
import ru.knowledge.mtstetaproject.movies.database.dto.ActorDto

class ActorsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val photo = view.findViewById<ImageView>(R.id.iv_actor_photo)
    private val name = view.findViewById<TextView>(R.id.tv_actor_name)

    fun bind(actor: ActorDto) {
        photo.load(actor.imageUrl)
        name.text = actor.name
    }
}