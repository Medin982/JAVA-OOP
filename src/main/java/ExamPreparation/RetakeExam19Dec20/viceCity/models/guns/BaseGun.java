package ExamPreparation.RetakeExam19Dec20.viceCity.models.guns;


import static ExamPreparation.RetakeExam19Dec20.viceCity.common.ExceptionMessages.*;

public abstract class BaseGun implements Gun {

    private String name;
    private int bulletsPerBarrel;
    private int totalBullets;
    private boolean canFire;
    private final int barrelCapacity;

    protected BaseGun(String name, int bulletsPerBarrel, int totalBullets) {
        this.setName(name);
        this.setBulletsPerBarrel(bulletsPerBarrel);
        this.barrelCapacity = bulletsPerBarrel;
        this.setTotalBullets(totalBullets);

    }

    protected void setTotalBullets(int totalBullets) {
        if (totalBullets < 0) {
            throw new IllegalArgumentException(TOTAL_BULLETS_LESS_THAN_ZERO);
        }
        this.totalBullets = totalBullets;
    }

    private void setBulletsPerBarrel(int bulletsPerBarrel) {
        if (bulletsPerBarrel < 0) {
            throw new IllegalArgumentException(BULLETS_LESS_THAN_ZERO);
        }
        this.bulletsPerBarrel = bulletsPerBarrel;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(NAME_NULL);
        }
        this.name = name;
    }

    public int getBarrelCapacity() {
       return this.barrelCapacity;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getBulletsPerBarrel() {
        return this.bulletsPerBarrel;
    }

    @Override
    public boolean canFire() {
        return this.totalBullets > 0;
    }

    @Override
    public int getTotalBullets() {
        return this.totalBullets;
    }

    @Override
    public int fire() {
        if (canFire()) {
            this.bulletsPerBarrel--;
            this.totalBullets--;
            if (bulletsPerBarrel <= 0) {
                this.setBulletsPerBarrel(this.getBarrelCapacity());
            }
            return 1;
        }
        return 0;
    }
}
