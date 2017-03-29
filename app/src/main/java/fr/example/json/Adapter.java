package fr.example.json;

import android.os.*; // Auto generate
import android.app.*; // Auto generate

import android.content.Context;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import android.support.v7.widget.RecyclerView;

import android.widget.TextView;
import android.widget.ImageView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>
{
	private Context context;
	private List<ItemData> item_data;
	
	public Adapter(Context context, List<ItemData> item_data)
	{
		this.context = context;
		this.item_data = item_data;
	}
	
	@Override
	public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null);
		
		ViewHolder viewHolder = new ViewHolder(view);
		
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(Adapter.ViewHolder holder, int position)
	{
		Glide.with(context)
		     .load(item_data.get(position).getImageUrl())
		     .into(holder.image);
			 
		holder.title.setText(item_data.get(position).getTitle());
		holder.text.setText(item_data.get(position).getText());
	}

	@Override
	public int getItemCount()
	{
		return item_data.size();
	}
	
	public class ViewHolder extends RecyclerView.ViewHolder
	{
		final ImageView image;
		final TextView title, text;
		
		ViewHolder(View itemView)
		{
			super(itemView);
			
			image = (ImageView)itemView.findViewById(R.id.id_image);
			title = (TextView)itemView.findViewById(R.id.id_title);
			text = (TextView)itemView.findViewById(R.id.id_text);
		}
	}
}
