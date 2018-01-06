package violentapplications.guitest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebastian on 2017-09-23.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.GoodViewHolder> {

    private Context context;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    private OnItemLongClickListener onItemLongClickListener;
    private OnItemClickListener onItemClickListener;

    public Adapter(Context context) {
        this.context = context;
    }

    public void setSoundList(List<Sound> list) {
        soundList.clear();
        soundList.addAll(list);
        notifyDataSetChanged();
    }

    List<Sound> soundList = new ArrayList<>();

    @Override
    public Adapter.GoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View soudItemView = LayoutInflater.from(context).inflate(R.layout.sound_item, parent, false);
        return new GoodViewHolder(soudItemView);
    }

    @Override
    public void onBindViewHolder(Adapter.GoodViewHolder holder, int position) {
        final Sound sound = soundList.get(position);
        holder.goodSound.setText(sound.getName());
        if (onItemClickListener != null) {
            holder.goodSound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(sound);
                }
            });
        }
        if (onItemLongClickListener != null) {
            holder.goodSound.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onItemLongClickListener.onItemLongClick(sound);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return soundList.size();
    }


    class GoodViewHolder extends RecyclerView.ViewHolder {

        private Button goodSound;
        private ImageView share;

        public GoodViewHolder(View itemView) {
            super(itemView);
            goodSound = (Button) itemView.findViewById(R.id.good_sound);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Sound item);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(Sound item);
    }
}
