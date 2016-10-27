package romz.luannguyen.mygetfirebase.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import romz.luannguyen.mygetfirebase.R;
import romz.luannguyen.mygetfirebase.model.Dulieu;

/**
 * Created by Admin on 10/27/2016.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.DuLieuViewHolder> {
    private static final String TAG = "SongAdapter";
    private List<Dulieu> mDulieus;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    class DuLieuViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;

        public DuLieuViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView)itemView.findViewById(R.id.imvHinh);

        }
    }

    public CustomAdapter(Context context, List<Dulieu> mDulieus) {
        this.mContext = context;
        this.mDulieus = mDulieus;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public DuLieuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_list, parent, false);
        return new DuLieuViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DuLieuViewHolder holder, int position) {
        Dulieu dulieu = mDulieus.get(position);
        Glide.with(mContext).load(dulieu.getUrl())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mDulieus.size();
    }

}
