package matej.petric.androidwarsapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WarsAdapter extends RecyclerView.Adapter<WarsAdapter.WarsViewHolder> {
    private Context mContext;
    private ArrayList<WarsItem> mWarsList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public WarsAdapter(Context context, ArrayList<WarsItem> warsList) {
        mContext = context;
        mWarsList = warsList;
    }

    @NonNull
    @Override
    public WarsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.wars_item, parent, false);
        return new WarsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WarsViewHolder holder, int position) {
        WarsItem currentItem = mWarsList.get(position);

        String name = currentItem.getName();
        int massCount = currentItem.getMass();
        int heightCount = currentItem.getHeight();

        holder.mTextViewCreator.setText(name);
        holder.mTextViewMass.setText("mass: " + massCount + " kg");
        holder.mTextViewHeight.setText("height: " + heightCount + " cm");
    }

    @Override
    public int getItemCount() {
        return mWarsList.size();
    }

    public class WarsViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextViewCreator;
        public TextView mTextViewMass;
        public TextView mTextViewHeight;
        public TextView mTextViewSkin;

        public WarsViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewCreator = itemView.findViewById(R.id.text_view_creator);
            mTextViewMass = itemView.findViewById(R.id.text_view_mass);
            mTextViewHeight = itemView.findViewById(R.id.text_view_height);
            mTextViewSkin = itemView.findViewById(R.id.text_view_skin);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }
}
