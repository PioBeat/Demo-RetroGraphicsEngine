package net.offbeatpioneer.demoapp.retrographicsengine;

import android.os.Build;
import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import net.offbeatpioneer.android.components.OffbeatButton;
import net.offbeatpioneer.android.components.OffbeatTextView;
import net.offbeatpioneer.demoapp.R;

import java.util.List;

import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import jp.wasabeef.recyclerview.animators.holder.AnimateViewHolder;

/**
 * @author Dominik Grzelak
 * @since 05.04.2017
 */

public class SampleListAdapter extends RecyclerView.Adapter<SampleListAdapter.ViewHolder> {

    private class VIEW_TYPES {
        public static final int Header = 1;
        public static final int Normal = 2;
        public static final int Footer = 3;
    }

    private List<SampleItem> dataset;
    private ViewGroup parentView;

    public SampleListAdapter(List<SampleItem> dataset) {
        this.dataset = dataset;
    }


    @Override
    public SampleListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        parentView = parent;
        View v = null;

        switch (viewType) {
            case VIEW_TYPES.Header:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item_header, parentView, false);
                break;
            default:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parentView, false);
                break;
        }
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SampleItem item = dataset.get(position);

        if (getItemViewType(position) == VIEW_TYPES.Header) {
            holder.description.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.description.setText(Html.fromHtml(item.getDescription()));
            } else {
                holder.description.setText(Html.fromHtml(item.getDescription()));
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ((LinearLayout) holder.description.getParent()).setBackgroundColor(parentView.getResources().getColor(item.backgroundColor, parentView.getContext().getTheme()));
            } else {
                ((LinearLayout) holder.description.getParent()).setBackgroundColor(parentView.getResources().getColor(item.backgroundColor));
            }
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.description.setText(Html.fromHtml(item.getDescription()));
        } else {
            holder.description.setText(Html.fromHtml(item.getDescription()));
        }

        ((LinearLayout) holder.description.getParent()).setOnClickListener(item.getClickListener());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ((LinearLayout) holder.description.getParent()).setBackgroundColor(parentView.getResources().getColor(item.backgroundColor, parentView.getContext().getTheme()));
        } else {
            ((LinearLayout) holder.description.getParent()).setBackgroundColor(parentView.getResources().getColor(item.backgroundColor));
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0)
            return VIEW_TYPES.Header;
//        else if(items.get(position).isFooter)
//            return VIEW_TYPES.Footer;
        else
            return VIEW_TYPES.Normal;

    }

    @Override
    public int getItemCount() {
        return this.dataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements AnimateViewHolder {
        public OffbeatTextView description;
        public OffbeatButton button;

        public ViewHolder(View itemView) {
            super(itemView);

            description = (OffbeatTextView) itemView.findViewById(R.id.textViewDescription);
            button = (OffbeatButton) itemView.findViewById(R.id.btnExample);
        }

        @Override
        public void preAnimateAddImpl(androidx.recyclerview.widget.RecyclerView.ViewHolder holder) {
            ViewCompat.setTranslationY(itemView, -itemView.getHeight() * 0.3f);
            ViewCompat.setAlpha(itemView, 0);
        }

        @Override
        public void preAnimateRemoveImpl(androidx.recyclerview.widget.RecyclerView.ViewHolder holder) {

        }

        @Override
        public void animateAddImpl(androidx.recyclerview.widget.RecyclerView.ViewHolder holder, androidx.core.view.ViewPropertyAnimatorListener listener) {
            ViewCompat.animate(itemView)
                    .translationY(0)
                    .alpha(1)
                    .setDuration(300)
                    .setListener(listener)
                    .start();
        }

        @Override
        public void animateRemoveImpl(androidx.recyclerview.widget.RecyclerView.ViewHolder holder, androidx.core.view.ViewPropertyAnimatorListener listener) {
            ViewCompat.animate(itemView)
                    .translationY(-itemView.getHeight() * 0.3f)
                    .alpha(0)
                    .setDuration(300)
                    .setListener(listener)
                    .start();
        }
    }
}
