package in.hussain.expensetrackerapi.controller;

import in.hussain.expensetrackerapi.entity.Expense;
import in.hussain.expensetrackerapi.service.ExpenseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExpenseController {

    private final ExpenseService service;

    public ExpenseController(ExpenseService service){
        this.service = service;
    }
    @GetMapping("/expenses")
    public List<Expense> getAllExpenses(Pageable page){
        return service.getAllExpense(page).toList();
    }

    @GetMapping("/expenses/{id}")
    public Expense getExpenseById(@PathVariable("id") Long id){
        return service.getExpenseById(id);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/expenses/{id}")
    public void deleteExpenseById(@PathVariable("id") Long id){
        service.deleteExpenseById(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/expenses")
    public Expense saveExpenseDetails(@RequestBody Expense expense){
        return service.saveExpenseDetails(expense);
    }
    @PutMapping("/expenses/{id}")
    public Expense updateExpenseDetails(@PathVariable("id") Long id, @RequestBody Expense expense){
        return service.updateExpenseDetails(id, expense);
    }
}
