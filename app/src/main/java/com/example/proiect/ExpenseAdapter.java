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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.MyViewHolder> implements Filterable {

    private ArrayList<Expense> expenseList;
    private ArrayList<Expense> expensesListAll;

    public ExpenseAdapter(ArrayList<Expense> expenseList){
        this.expenseList = expenseList;
        this.expensesListAll = new ArrayList<>(expenseList);
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Expense> filteredList = new ArrayList<>();
            if(charSequence.toString().isEmpty()){
                filteredList.addAll(expensesListAll);
            } else {
                for (Expense expense : expensesListAll){
                    if(expense.getExpenseTitle().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        filteredList.add(expense);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            expenseList.clear();
            expenseList.addAll((Collection<? extends Expense>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView expenseTitle;
        private TextView expenseDetails;
        private TextView expenseAmount;

        public MyViewHolder(final View view) {
            super(view);
            this.expenseTitle = view.findViewById(R.id.expenseText);
            this.expenseDetails = view.findViewById(R.id.expenseDetails);
            this.expenseAmount = view.findViewById(R.id.expenseAmount);
        }
    }


    @NonNull
    @Override
    public ExpenseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseAdapter.MyViewHolder holder, int position) {
        String expenseTitle = expenseList.get(position).getExpenseTitle();
        String expenseDetails = expenseList.get(position).getExpenseDetails();
        String expenseAmount = Float.valueOf(expenseList.get(position).getAmount()).toString();
        holder.expenseTitle.setText(expenseTitle);
        holder.expenseDetails.setText(expenseDetails);
        holder.expenseAmount.setText(expenseAmount);
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }
}
