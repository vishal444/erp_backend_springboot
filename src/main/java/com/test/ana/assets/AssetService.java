package com.test.ana.assets;

import com.test.ana.user.User;
import com.test.ana.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AssetService {
    @Autowired
    private final AssetRepository assetRepository;
    @Autowired
    private final UserRepository userRepository;

    public void saveAssetData(Assets asset) {
        User user = userRepository.findByEmail(asset.getUserName()).orElseThrow(() -> new IllegalStateException("no such user"));
        asset.setUser(user);
        assetRepository.save(asset);
    }
    @Transactional
    public void updateCapital(double amount, String userName) {
// dumb->       Assets assets = assetRepository.findById(0).orElseThrow(() -> new IllegalStateException("no such asset"));
        User user = userRepository.findByEmail(userName).orElseThrow(() -> new IllegalStateException("no such user"));
        Assets assets = assetRepository.getAssetByUser(user);
        double capital = assetRepository.getSumCapital(user);
        double updatedCapital = capital - amount;
        assets.setCapital(updatedCapital);
        assetRepository.save(assets);
    }

    public Assets getAllAssets(String userName) {
        User user = userRepository.findByEmail(userName).orElseThrow(() -> new IllegalStateException("no such user"));
        return assetRepository.getAssetByUser(user);
    }
    @Transactional
    public void updateAsset(String columnName, double newValue, String userName) {
        User user = userRepository.findByEmail(userName).orElseThrow(() -> new IllegalStateException("no such user"));
        Assets asset = assetRepository.getAssetByUser(user);
        switch (columnName) {
            case "furniture" -> asset.setFurniture(newValue);
            case "machinery" -> asset.setMachinery(newValue);
            case "land" -> asset.setLand(newValue);
            case "building" -> asset.setBuilding(newValue);
            case "equipments" -> asset.setEquipments(newValue);
            case "motorVehicles" -> asset.setMotorVehicles(newValue);
            case "bankDeposits" -> asset.setBankDeposits(newValue);
            case "investments" -> asset.setInvestments(newValue);
        }
    }
}
