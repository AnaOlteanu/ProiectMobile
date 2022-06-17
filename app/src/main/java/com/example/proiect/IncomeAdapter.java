package com.example.proiect;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proiect.Entities.Expense;
import com.example.proiect.Entities.Income;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.MyViewHolder> implements Filterable {

    private ArrayList<Income> incomeList;
    private ArrayList<Income> incomeListAll;

    public IncomeAdapter(ArrayList<Income> incomeList) {
        this.incomeList = incomeList;
        this.incomeListAll = new ArrayList<>(incomeList);
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Income> filteredList = new ArrayList<>();
            if(charSequence.toString().isEmpty()){
                filteredList.addAll(incomeListAll);
            } else {
                for (Income income : incomeListAll){
                    if(income.getIncomeTitle().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        filteredList.add(income);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            incomeList.clear();
            incomeList.addAll((Collection<? extends Income>) filterResults.values);
            notifyDataSetChanged();
        }
    };


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView incomeTitle;
        private TextView incomeDetails;
        private TextView amount;

        public MyViewHolder(final View view) {
            super(view);
            this.incomeTitle = view.findViewById(R.id.incomeText);
            this.incomeDetails = view.findViewById(R.id.incomeDetails);
            this.amount = view.findViewById(R.id.incomeAmount);
        }
    }




    @NonNull
    @Override
    public IncomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.income_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IncomeAdapter.MyViewHolder holder, int position) {
        String incomeTitle = incomeList.get(position).getIncomeTitle();
        String incomeDetails = incomeList.get(position).getIncomeDetails();
        String incomeAmount = Float.valueOf(incomeList.get(position).getAmount()).toString();
        holder.incomeTitle.setText(incomeTitle);
        holder.incomeDetails.setText(incomeDetails);
        holder.amount.setText(incomeAmount);
    }

    @Override
    public int getItemCount() {
        return incomeList.size();
    }
}
