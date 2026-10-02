package in.org.dig.induslockbox.controller;

import in.org.dig.induslockbox.aspect.ProfileExecution;
import in.org.dig.induslockbox.entity.Bank;
import in.org.dig.induslockbox.service.BankService;
import in.org.dig.platform.LoggerUtil.util.EventLogDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/banks")
public class BankController {

    @Autowired
    private BankService bankService;

    @ProfileExecution
    @EventLogDetails(eventId = "fetchallbanks", eventName = "fetch_all_banks", eventDescription = "fetch all banks", serviceName = "DIGI_LOCKER")
    @GetMapping("/fetchall")
    public List<Bank> getAllBanks() {
        return bankService.findAll();
    }

    @ProfileExecution
    @EventLogDetails(eventId = "fetchbankbyid", eventName = "fetch_bank_by_id", eventDescription = "fetch bank by id", serviceName = "DIGI_LOCKER")
    @GetMapping("/fetchbyid/{id}")
    public ResponseEntity<Bank> getBankById(@PathVariable Long id) {
        Optional<Bank> bank = bankService.findById(id);
        return bank.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ProfileExecution
    @EventLogDetails(eventId = "fetchbankbycompany", eventName = "fetch_bank_by_company", eventDescription = "fetch banks by company id", serviceName = "DIGI_LOCKER")
    @GetMapping("/company/{company_id}")
    public ResponseEntity<List<Bank>> getBankByCompanyId(@PathVariable Long company_id) {
        List<Bank> bankDetails = bankService.findByCompanyId(company_id);
        return ResponseEntity.ok(bankDetails);
    }

    @ProfileExecution
    @EventLogDetails(eventId = "savebank", eventName = "save_bank", eventDescription = "create new bank", serviceName = "DIGI_LOCKER")
    @PostMapping("/save")
    public Bank createBank(@RequestBody Bank bank) {
        return bankService.save(bank);
    }

    @ProfileExecution
    @EventLogDetails(eventId = "updatebank", eventName = "update_bank", eventDescription = "update bank details", serviceName = "DIGI_LOCKER")
    @PutMapping("/update/{id}")
    public Bank updateBank(@PathVariable Long id, @RequestBody Bank updatedBank) {
        return bankService.updateBank(id, updatedBank);
    }

    @ProfileExecution
    @EventLogDetails(eventId = "deletebank", eventName = "delete_bank", eventDescription = "delete bank", serviceName = "DIGI_LOCKER")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBank(@PathVariable Long id) {
        bankService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @ProfileExecution
    @EventLogDetails(eventId = "activatebank", eventName = "activate_bank", eventDescription = "activate bank", serviceName = "DIGI_LOCKER")
    @PatchMapping("/activate/{id}")
    public void activateBank(@PathVariable Long id) {
        bankService.activateById(id);
    }

    @ProfileExecution
    @EventLogDetails(eventId = "decryptloginpassword", eventName = "decrypt_login_password", eventDescription = "decrypt bank login password", serviceName = "DIGI_LOCKER")
    @GetMapping("/decrypt/LoginPassword/{id}")
    public String getDecryptLoginPassword(@PathVariable Long id) {
        return bankService.GetDecryptLoginPassword(id);
    }

    @ProfileExecution
    @EventLogDetails(eventId = "decrypttransactionpassword", eventName = "decrypt_transaction_password", eventDescription = "decrypt bank transaction password", serviceName = "DIGI_LOCKER")
    @GetMapping("/decrypt/TransactionPassword/{id}")
    public String getDecryptTransactionPassword(@PathVariable Long id) {
        return bankService.GetDecryptTransactionPassword(id);
    }
}