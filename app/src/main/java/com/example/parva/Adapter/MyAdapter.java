package com.example.parva.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parva.Details;
import com.example.parva.MainActivity;
import com.example.parva.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    ArrayList<Details> details;
    public static final int TYPE_ITEM = 1;
    public static final int TYPE_FOOT = 2;

    public MyAdapter(Context mContext, ArrayList<Details> details) {
        this.mContext = mContext;
        this.details = details;
    }

    public MyAdapter() {
    }
    public boolean isFooter(int position) {
        return position == details.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            //Inflating recycle view item layout
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
            return new ViewHolder(itemView);
        }
        else  {
            //Inflating footer view
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.footer_rv, parent, false);
            return new FooterViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerHolder = (FooterViewHolder) holder;
           // footerHolder.footerText.setText("Load");
            footerHolder.footerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mContext instanceof MainActivity) {
                        ((MainActivity)mContext).loadData();
                    }
                    //Toast.makeText(mContext,"Press",Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            Details detail = details.get(position);
            viewHolder.author.setText(detail.getPost_author_name());
            viewHolder.description.setText(detail.getPost_description());
            viewHolder.postTitle.setText(detail.getPost_title());
            viewHolder.community.setText(detail.getCommunity_title());
            Picasso.get().load(detail.getPost_thumbnail()).into(viewHolder.imageView);

            if(position%2==0){
                viewHolder.title.setText(R.string.trump);
            }
            else {
                viewHolder.title.setText(R.string.random_event);
            }
        }
    }

    @Override
    public int getItemCount() {
        return details.size()+1;
    }
    @Override
    public int getItemViewType(int position) {
       return isFooter(position) ? TYPE_FOOT : TYPE_ITEM;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView author,description,postTitle,title,community;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            author=itemView.findViewById(R.id.nameAuthor);
            description=itemView.findViewById(R.id.description);
            postTitle=itemView.findViewById(R.id.postTitle);
            imageView=itemView.findViewById(R.id.image);
            title=itemView.findViewById(R.id.trumpTitle);
            community=itemView.findViewById(R.id.community);
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        Button footerButton;

        public FooterViewHolder(View itemView) {
            super(itemView);
            footerButton = itemView.findViewById(R.id.footerButton);
        }
    }
}


