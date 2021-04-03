package com.kotlinMaster.movie_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kotlinMaster.movie_app.dataholder.Actor

class ActorsAdapter : RecyclerView.Adapter<ActorViewHolder>() {
    private var actors = listOf<Actor>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        return ActorViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_actor, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.onBind(actors[position])
    }

    override fun getItemCount(): Int = actors.size

    fun bindActors(newActors: List<Actor>) {
        actors = newActors
        notifyDataSetChanged()
    }
}

class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val picture = itemView.findViewById<ImageView>(R.id.actor_picture)
    private val name = itemView.findViewById<TextView>(R.id.actor_name)

    fun onBind(actor: Actor) {
        //picture.load(actor.imageUrl)
        Glide.with(context)
            .load(actor.imageUrl)
            .into(picture)

        name.text = actor.name
    }
}
private val RecyclerView.ViewHolder.context
    get() = this.itemView.context