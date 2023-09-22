        package solvademo.solva.controller;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;
        import solvademo.solva.dto.TransactionDTO;
        import solvademo.solva.sevice.TransactionService;

        import java.util.List;

        @RequestMapping("/api/transactions")
        @RestController
        public class TransactionController {

            @Autowired
            private TransactionService transactionService;


            @PostMapping
            public void saveTransaction(@RequestBody TransactionDTO transactionDTO) {
                transactionService.saveTransaction(transactionDTO);
            }

            @GetMapping("/exceeding-limit")
            public List<TransactionDTO> getTransactionsExceedingLimit() {
                return transactionService.getTransactionsExceedingLimit();
            }
        }
