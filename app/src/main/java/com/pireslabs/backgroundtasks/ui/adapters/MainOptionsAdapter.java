package com.pireslabs.backgroundtasks.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pireslabs.backgroundtasks.R;
import com.pireslabs.backgroundtasks.ui.AppActivities;

public class MainOptionsAdapter extends RecyclerView.Adapter<MainOptionsAdapter.OptionViewHolder> {

    public static final class ItemOption {

        AppActivities activity;

        public ItemOption(AppActivities activity) {
            this.activity = activity;
        }

        int getIconRes() {
            return this.activity.getIconRes();
        }

        String getActivityDescription() {
            return this.activity.getActivityDescription();
        }

        Class getActivityClass() {
            return this.activity.getActivityClass();
        }
    }

    private final ItemOption[] options;

    private final LayoutInflater layoutInflater;

    public MainOptionsAdapter(Context context, ItemOption[] options) {
        this.options = options;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public OptionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View itemView = this.layoutInflater.inflate(R.layout.card_view_option, viewGroup, false);
        return new OptionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OptionViewHolder optionViewHolder, int i) {
        final CardView item = optionViewHolder.itemView.findViewById(R.id.card_view_option);
        final ItemOption option = this.options[i];
        item.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), option.getActivityClass());
            view.getContext().startActivity(intent);
        });
        optionViewHolder.setOption(option);
    }

    @Override
    public int getItemCount() {
        if (this.options == null) {
            return 0;
        }
        return this.options.length;
    }

    static final class OptionViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgvwIcon;

        private final TextView txtvwDescription;

        private Context context;

        OptionViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imgvwIcon = itemView.findViewById(R.id.imgvw_option_icon);
            this.txtvwDescription = itemView.findViewById(R.id.txtvw_option_description);
            this.context = itemView.getContext();
        }

        void setOption(ItemOption itemOption) {
            this.imgvwIcon.setImageDrawable(this.context.getResources().getDrawable(itemOption.getIconRes()));
            this.txtvwDescription.setText(itemOption.getActivityDescription());
        }
    }
}
