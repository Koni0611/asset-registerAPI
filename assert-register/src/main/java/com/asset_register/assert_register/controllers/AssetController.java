package com.asset_register.assert_register.controllers;

import com.asset_register.assert_register.models.ComputerDetails;
import com.asset_register.assert_register.models.DongleAndWifi;
import com.asset_register.assert_register.models.MobilePhoneDetails;
import com.asset_register.assert_register.models.Printer;
import com.asset_register.assert_register.repositories.ComputerDetailsRepository;
import com.asset_register.assert_register.repositories.DongleAndWifiRepository;
import com.asset_register.assert_register.repositories.MobilePhoneDetailsRepository;
import com.asset_register.assert_register.repositories.PrinterRepository;
import com.asset_register.assert_register.services.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    @Autowired
    private ComputerDetailsRepository computerDetailsRepository;

    @Autowired
    private MobilePhoneDetailsRepository mobilePhoneDetailsRepository;

    @Autowired
    private PrinterRepository printerRepository;

    @Autowired
    private DongleAndWifiRepository dongleWifiRepository;

    @Autowired
    private AuthService authService;

    // Fetch all assets for a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, List<Object>>> getUserAssets(@PathVariable Long userId) {
        Map<String, List<Object>> assets = new HashMap<>();

        System.out.println("Fetching assets for userId: " + userId);

        List<ComputerDetails> computers = computerDetailsRepository.findByUserId(userId);
        List<MobilePhoneDetails> mobiles = mobilePhoneDetailsRepository.findByUserId(userId);
        List<Printer> printers = printerRepository.findByUserId(userId);
        List<DongleAndWifi> dongles = dongleWifiRepository.findByUserId(userId);

        assets.put("computers", new ArrayList<>(computers));
        assets.put("mobiles", new ArrayList<>(mobiles));
        assets.put("printers", new ArrayList<>(printers));
        assets.put("dongles", new ArrayList<>(dongles));

        return ResponseEntity.ok(assets);
    }

    // New: Fetch a single asset by type and ID
    @GetMapping("/{type}/{id}")
    public ResponseEntity<Object> getAssetById(@PathVariable String type, @PathVariable Long id) {
        switch (type.toLowerCase()) {
            case "computers":
                return computerDetailsRepository.findById(id)
                        .map(asset -> ResponseEntity.ok((Object) asset))
                        .orElse(ResponseEntity.notFound().build());
            case "mobiles":
                return mobilePhoneDetailsRepository.findById(id)
                        .map(asset -> ResponseEntity.ok((Object) asset))
                        .orElse(ResponseEntity.notFound().build());
            case "printers":
                return printerRepository.findById(id)
                        .map(asset -> ResponseEntity.ok((Object) asset))
                        .orElse(ResponseEntity.notFound().build());
            case "dongles":
                return dongleWifiRepository.findById(id)
                        .map(asset -> ResponseEntity.ok((Object) asset))
                        .orElse(ResponseEntity.notFound().build());
            default:
                return ResponseEntity.badRequest().body("Invalid asset type: " + type);
        }
    }

    // Methods for ComputerDetails
    @PostMapping("/computers")
    public ResponseEntity<ComputerDetails> addComputer(@RequestBody ComputerDetails computerDetails) {
        try {
            Long userId = authService.getCurrentUser().getId();
            computerDetails.setUserId(userId);
            ComputerDetails savedComputer = computerDetailsRepository.save(computerDetails);
            return ResponseEntity.ok(savedComputer);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(401).body(null);
        }
    }

    @PutMapping("/computers/{id}")
    public ResponseEntity<ComputerDetails> updateComputer(@PathVariable Long id,
            @RequestBody ComputerDetails computerDetails) {
        Optional<ComputerDetails> optionalComputer = computerDetailsRepository.findById(id);
        if (optionalComputer.isPresent()) {
            ComputerDetails existingComputer = optionalComputer.get();
            existingComputer.setComputerForm(computerDetails.getComputerForm());
            existingComputer.setComputerManufacturer(computerDetails.getComputerManufacturer());
            existingComputer.setComputerModelName(computerDetails.getComputerModelName());
            existingComputer.setComputerModelNumber(computerDetails.getComputerModelNumber());
            existingComputer.setSerialNumber(computerDetails.getSerialNumber());
            existingComputer.setOperationSystem(computerDetails.getOperationSystem());
            existingComputer.setProcessor(computerDetails.getProcessor());
            existingComputer.setRandomAccesMemory(computerDetails.getRandomAccesMemory());
            existingComputer.setHardDriveCapacity(computerDetails.getHardDriveCapacity());
            existingComputer.setMonitorManufacturer(computerDetails.getMonitorManufacturer());
            existingComputer.setKeyboardManufacturer(computerDetails.getKeyboardManufacturer());
            existingComputer.setMouseManufacturer(computerDetails.getMouseManufacturer());
            existingComputer.setUserId(computerDetails.getUserId());
            computerDetailsRepository.save(existingComputer);
            return ResponseEntity.ok(existingComputer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/computers/{id}")
    public ResponseEntity<Void> deleteComputer(@PathVariable Long id) {
        Optional<ComputerDetails> optionalComputer = computerDetailsRepository.findById(id);
        if (optionalComputer.isPresent()) {
            computerDetailsRepository.delete(optionalComputer.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Methods for MobilePhoneDetails
    @PostMapping("/mobiles")
    public ResponseEntity<MobilePhoneDetails> addMobile(@RequestBody MobilePhoneDetails mobilePhoneDetails) {
        try {
            Long userId = authService.getCurrentUser().getId();
            mobilePhoneDetails.setUserId(userId);
            MobilePhoneDetails savedMobile = mobilePhoneDetailsRepository.save(mobilePhoneDetails);
            return ResponseEntity.ok(savedMobile);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(401).body(null);
        }
    }

    @PutMapping("/mobiles/{id}")
    public ResponseEntity<MobilePhoneDetails> updateMobile(@PathVariable Long id,
            @RequestBody MobilePhoneDetails mobilePhoneDetails) {
        Optional<MobilePhoneDetails> optionalMobile = mobilePhoneDetailsRepository.findById(id);
        if (optionalMobile.isPresent()) {
            MobilePhoneDetails existingMobile = optionalMobile.get();
            existingMobile.setMobilePhoneManufacturer(mobilePhoneDetails.getMobilePhoneManufacturer());
            existingMobile.setMobilePhoneModel(mobilePhoneDetails.getMobilePhoneModel());
            existingMobile.setMobilePhoneImeiNumber(mobilePhoneDetails.getMobilePhoneImeiNumber());
            existingMobile.setMobilePhoneSerialNumber(mobilePhoneDetails.getMobilePhoneSerialNumber());
            existingMobile.setMobileNumber(mobilePhoneDetails.getMobileNumber());
            existingMobile.setMobileNumberOperator(mobilePhoneDetails.getMobileNumberOperator());
            existingMobile.setUserId(mobilePhoneDetails.getUserId());
            mobilePhoneDetailsRepository.save(existingMobile);
            return ResponseEntity.ok(existingMobile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/mobiles/{id}")
    public ResponseEntity<Void> deleteMobile(@PathVariable Long id) {
        Optional<MobilePhoneDetails> optionalMobile = mobilePhoneDetailsRepository.findById(id);
        if (optionalMobile.isPresent()) {
            mobilePhoneDetailsRepository.delete(optionalMobile.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Methods for Printer
    @PostMapping("/printers")
    public ResponseEntity<Printer> addPrinter(@RequestBody Printer printer) {
        try {
            Long userId = authService.getCurrentUser().getId();
            printer.setUserId(userId);
            Printer savedPrinter = printerRepository.save(printer);
            return ResponseEntity.ok(savedPrinter);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(401).body(null);
        }
    }

    @PutMapping("/printers/{id}")
    public ResponseEntity<Printer> updatePrinter(@PathVariable Long id, @RequestBody Printer printer) {
        Optional<Printer> optionalPrinter = printerRepository.findById(id);
        if (optionalPrinter.isPresent()) {
            Printer existingPrinter = optionalPrinter.get();
            existingPrinter.setPrinterManufacturer(printer.getPrinterManufacturer());
            existingPrinter.setPrinterModel(printer.getPrinterModel());
            existingPrinter.setPrinterSerilaNumber(printer.getPrinterSerilaNumber());
            existingPrinter.setUserId(printer.getUserId());
            printerRepository.save(existingPrinter);
            return ResponseEntity.ok(existingPrinter);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/printers/{id}")
    public ResponseEntity<Void> deletePrinter(@PathVariable Long id) {
        Optional<Printer> optionalPrinter = printerRepository.findById(id);
        if (optionalPrinter.isPresent()) {
            printerRepository.delete(optionalPrinter.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Methods for DongleAndWifi
    @PostMapping("/dongles")
    public ResponseEntity<DongleAndWifi> addDongle(@RequestBody DongleAndWifi dongleAndWifi) {
        try {
            Long userId = authService.getCurrentUser().getId();
            dongleAndWifi.setUserId(userId);
            DongleAndWifi savedDongle = dongleWifiRepository.save(dongleAndWifi);
            return ResponseEntity.ok(savedDongle);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(401).body(null);
        }
    }

    @PutMapping("/dongles/{id}")
    public ResponseEntity<DongleAndWifi> updateDongle(@PathVariable Long id, @RequestBody DongleAndWifi dongleAndWifi) {
        Optional<DongleAndWifi> optionalDongle = dongleWifiRepository.findById(id);
        if (optionalDongle.isPresent()) {
            DongleAndWifi existingDongle = optionalDongle.get();
            existingDongle.setDongleOrRouterManfacturer(dongleAndWifi.getDongleOrRouterManfacturer());
            existingDongle.setDongleOrRouterModel(dongleAndWifi.getDongleOrRouterModel());
            existingDongle.setDongleOrRouterImeiNumber(dongleAndWifi.getDongleOrRouterImeiNumber());
            existingDongle.setDongleOrRouterSerialNumber(dongleAndWifi.getDongleOrRouterSerialNumber());
            existingDongle.setMobileNumber(dongleAndWifi.getMobileNumber());
            existingDongle.setMobileNumberOperator(dongleAndWifi.getMobileNumberOperator());
            existingDongle.setUserId(dongleAndWifi.getUserId());
            dongleWifiRepository.save(existingDongle);
            return ResponseEntity.ok(existingDongle);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/dongles/{id}")
    public ResponseEntity<Void> deleteDongle(@PathVariable Long id) {
        Optional<DongleAndWifi> optionalDongle = dongleWifiRepository.findById(id);
        if (optionalDongle.isPresent()) {
            dongleWifiRepository.delete(optionalDongle.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
