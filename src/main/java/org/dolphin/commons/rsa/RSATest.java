package org.dolphin.commons.rsa;

import java.security.PrivateKey;
import java.security.PublicKey;

public class RSATest {

	public static void main(String[] args) throws Exception {
        // 1、初始化秘钥
        String priKeyStr = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCnlzguoiYd/dDXoqQ1K5nVfj1ZUjUgTU3KetZF+cR9uhmQq2bhBK2QPBCfj09X4ejUs08EMyp0KalVZgzIU0XYRIdzunQyWx6cRO3V7GY08m4nUSg0HqAP9+1lQ45yaULo7jnhINXcN5B/Zxp4AJ/oHWLV7p/cwydZoTkJJ+tysovAqzZyKFtZKAqaA7G61xM/HQ7+muP9xYKs36cx6E2YAi1qtbgp12//AdLlMoYSxPR/3d6s0fujgosk28zFkHKSXfgJsvG+CJFbzZ1sAu+LQtr6oxTOi8m06m96o6/UwOeduC1hKNzmEjI5MWGWdYrRIl7/nHaSDd1e0BjnbsE9AgMBAAECggEAQliqZEPLJUprWELKEUaekxd0AB18bLpN1qAEnhVARancHxrMEr8AWz58Mj7Q4XDM9Q+7c2UyJGSt5eINo2zcFL8khJtHDvxlxOXA5lS9La7gDu6hJOkO4uN69wDkrsSfOYm+HjMalyDkMpvFrcZJh+HCVAExBnu341LhOo+0NWZ3q/MxKV2i9FrVsDKVuWRb18ERfQDqgMPcD5bewW6AZucwz3rOL720wCXFuSmrJRvpdBmQuj7RPdAyufc+mITAsn2zad9O8pcJOrd8mAev0iyU5kF6J67avDSquZQRe+g/b8xgX4kKDA8yfW+K/tFNGxLxkaZUBMWEPgSmCHr6wQKBgQDXuiHYiZnJiWg9trC8lVLq5R5KLAXWk/SswFoGR+NZz4P8YWbNofEGvYntztKnntfhcZuGNZjiX46G8+jb6xA7iISFCuoTvlVHZ9ESxgSmbGqfeRRDibcSNTPQQ/OD9/Swn0g++JrtuNEvbNkGyvJG4TywgUzSefdlsdmtPvTw7QKBgQDG4JbfqggUL0OdD+uZ7RX9bcoREHvldwOnDRV3eDryP/J6LxK7Bo3jo037JA5iTt/0orLSTj1qZ9hYCiHkeQVjW9cpNr5IGZdyJ13tE6cVEsNxIAdKMA0GFtKqKeH/y+2AtcIw5K/6yg7uK34Xf5A2MEn2ttSXsztKhNMYmKEXkQKBgEdbbEKiEVqWtwJi4ccfZZQJQaGrOismObLC1YRqVwuoFrJuzYCLZpyiKXOeDBQh+Kq714PnSrDmhtCg8Y99C5uFpsATQiZRuQerkX6yPWuHRSWG+Wr1rLa7Hm5va6dlZ2zc4xrhRO4JSjhzur4Cd212p4qC2pRsf6cF00j5IDBpAoGAd+ACMRkdO4VtvOtOuP5mZdbamMSV2a+OPRAu409OQJz4/FsJcEA4IIFbzMhj/y/J4Hx9SzSr4kpr5k1dNokYDVbbb1U3BuCeH8nI3NEewh4IPlTu7/Hct9Hj83vBZN038IAeaDhANuu6aic/HszeUTt0Kcj0hegcEgPT9hxx5XECgYEAokHcnnurVNVxi1SYKsy12NQJePkKvwCMiKe2OqUc7gjAjdZ9YaVBUZhzPHtGSoeDMLyEGD7A6SgUCOJHZmqx3adPY3vzupGl9DR5t/XxEtAhHjakB+q0/Du40i6UtRKuqEwRscloTDKI6XDwiYg4sArOMOHPnOqhssONwA9eoWI=";
        PrivateKey priKey = RSAUtil.initPrivateKeyByContent(priKeyStr);

        String pubKeyStr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAp5c4LqImHf3Q16KkNSuZ1X49WVI1IE1NynrWRfnEfboZkKtm4QStkDwQn49PV+Ho1LNPBDMqdCmpVWYMyFNF2ESHc7p0MlsenETt1exmNPJuJ1EoNB6gD/ftZUOOcmlC6O454SDV3DeQf2caeACf6B1i1e6f3MMnWaE5CSfrcrKLwKs2cihbWSgKmgOxutcTPx0O/prj/cWCrN+nMehNmAItarW4Kddv/wHS5TKGEsT0f93erNH7o4KLJNvMxZBykl34CbLxvgiRW82dbALvi0La+qMUzovJtOpveqOv1MDnnbgtYSjc5hIyOTFhlnWK0SJe/5x2kg3dXtAY527BPQIDAQAB";
        PublicKey publicKey = RSAUtil.initPublicKeyByContent(pubKeyStr);	
        
        String str = "RSA公钥私钥互相加解密";
        
        String priKeyEncryptStr = RSAUtil.encrypt(str, "UTF-8", priKey);
        System.out.println("私钥加密后密文:"+priKeyEncryptStr);
        System.out.println("公钥解密后明文:"+RSAUtil.decrypt(priKeyEncryptStr, "UTF-8", publicKey));
        
        
        String publicKeyEncryptStr = RSAUtil.encrypt(str, "UTF-8", publicKey);
        System.out.println("公钥加密后密文:"+publicKeyEncryptStr);
        System.out.println("私钥解密后明文:"+RSAUtil.decrypt(publicKeyEncryptStr, "UTF-8", priKey));
	}

}
