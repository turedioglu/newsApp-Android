package com.example.newsapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.Detailed;
import com.example.newsapp.Interface.ItemClickListener;
import com.example.newsapp.Item;
import com.example.newsapp.R;
import com.example.newsapp.RssObject;

class FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    public TextView txtTitle, txtPubDate, txtContent;
    private ItemClickListener itemClickListener;

    public FeedViewHolder (View itemView){
        super(itemView);

        txtTitle = (TextView)itemView.findViewById(R.id.txtTitle);
        txtPubDate = (TextView)itemView.findViewById(R.id.txtPubDate);
        txtContent = (TextView)itemView.findViewById(R.id.txtContent);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view){
            itemClickListener.onClick(view,getAdapterPosition(),false);
    }

    @Override
    public boolean onLongClick(View view){
        itemClickListener.onClick(view,getAdapterPosition(),true);
        return true;
    }
}

public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder> {

    private RssObject rssObject;
    private Context mContext;
    private LayoutInflater inflater;


    public FeedAdapter(RssObject rssObject, Context mContext){
        this.rssObject = rssObject;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item,parent,false);
        return new FeedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {

        holder.txtTitle.setText(rssObject.getItems().get(position).getTitle());
        holder.txtPubDate.setText(rssObject.getItems().get(position).getPubDate());
        holder.txtContent.setText(rssObject.getItems().get(position).getAuthor());


          final Item i = rssObject.items.get(position);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(!isLongClick){

                    Intent intent = new Intent(mContext, Detailed.class);
                   /*
                    Bu kısım eger native olarak haberler görünsün istersek hangi bilgileri detail ekranına yollacagımızı gösterir
                    intent.putExtra("title",i.getTitle());
                    intent.putExtra("pubDate",i.getPubDate());
                    intent.putExtra("content",i.getAuthor());
                    intent.putExtra("description",i.getDescription());
                     */

                    intent.putExtra("url",i.getLink());

                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);


                  /*
                   Bu kısım tıklandıgında direk WebView sayfaya yönlendirir.
                  Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(rssObject.getItems().get(position).getLink()));
                    browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   mContext.startActivity(browserIntent); */
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return rssObject.items.size();
    }
}
