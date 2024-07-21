package in.hussain.expensetrackerapi.service.serviceImpl;

import in.hussain.expensetrackerapi.entity.Expense;
import in.hussain.expensetrackerapi.repository.ExpenseRepository;
import in.hussain.expensetrackerapi.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository repository;

    @Autowired
    public  ExpenseServiceImpl(ExpenseRepository repository){
        this.repository = repository;
    }

    @Override
    public Page<Expense> getAllExpense(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    public Expense getExpenseById(Long id) {
        Optional<Expense> expense = repository.findById(id);
        if(expense.isPresent()){
            return expense.get();
        }
        throw new RuntimeException("Expense is not found for the id " + id);
    }

    @Override
    public void deleteExpenseById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Expense saveExpenseDetails(Expense expense) {
        return repository.save(expense);
    }

    @Override
    public Expense updateExpenseDetails(Long id, Expense expense) {
        Expense existingExpense = getExpenseById(id);
        existingExpense.setName(expense.getName() != null ? expense.getName() : existingExpense.getName());
        existingExpense.setAmount(expense.getAmount() != null ? expense.getAmount() : existingExpense.getAmount());
        existingExpense.setDescription(expense.getDescription() !=null ? expense.getDescription() : existingExpense.getDescription());
        existingExpense.setDate(expense.getDate() != null ? expense.getDate() : existingExpense.getDate());
        existingExpense.setCategory(expense.getCategory() != null ? expense.getCategory() : existingExpense.getCategory());
        return repository.save(existingExpense);
    }
}
