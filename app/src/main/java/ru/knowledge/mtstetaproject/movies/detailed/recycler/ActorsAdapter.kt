package ru.knowledge.mtstetaproject.movies.detailed.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.knowledge.mtstetaproject.R
import ru.knowledge.mtstetaproject.movies.database.dto.ActorDto

class ActorsAdapter : RecyclerView.Adapter<ActorsViewHolder>() {

    private var actorsList: MutableList<ActorDto> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_actor, parent, false)
        return ActorsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        holder.bind(actorsList[position])
    }

    override fun getItemCount(): Int = actorsList.size

    fun setActors(actors: List<ActorDto>) {
        actorsList.clear()
        actorsList.addAll(actors)
        notifyDataSetChanged()
    }
}