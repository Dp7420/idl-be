package in.org.dig.induslockbox.controller;

import java.util.List;
import java.util.Optional;

import in.org.dig.induslockbox.aspect.ProfileExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.org.dig.induslockbox.entity.Bank;
import in.org.dig.induslockbox.service.BankService;

@RestController
@RequestMapping("/api/admin/banks")
public class BankController {

    @Autowired
    private BankService bankService;

    @ProfileExecution
    @GetMapping("/fetchall")
    public List<Bank> getAllBanks() {
        return bankService.findAll();
    }

    @ProfileExecution
    @GetMapping("/fetchbyid/{id}")
    public ResponseEntity<Bank> getBankById(@PathVariable Long id) {
        Optional<Bank> bank = bankService.findById(id);
        return bank.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ProfileExecution
    @GetMapping("/company/{company_id}")
    public ResponseEntity<List<Bank>> getBankByCompanyId(@PathVariable Long company_id) {
        List<Bank> bankDetails = bankService.findByCompanyId(company_id);
        return ResponseEntity.ok(bankDetails);
    }

    @PostMapping("/save")
    public Bank createBank(@RequestBody Bank bank) {
        return bankService.save(bank);
    }

    @PutMapping("/update/{id}")
    public Bank updateBank(@PathVariable Long id, @RequestBody Bank updatedBank) {
        return bankService.updateBank(id, updatedBank);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBank(@PathVariable Long id) {
        bankService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
    @PatchMapping("/activate/{id}")
    public void activateBank(@PathVariable Long id) {
        bankService.activateById(id);
    }
    
    @GetMapping("/decrypt/LoginPassword/{id}")
    public String getDecryptLoginPassword(@PathVariable Long id) {
    	return bankService.GetDecryptLoginPassword(id);
    }
    
    @GetMapping("/decrypt/TransactionPassword/{id}")
    public String getDecryptTransactionPassword(@PathVariable Long id) {
    	return bankService.GetDecryptTransactionPassword(id);
    }
}