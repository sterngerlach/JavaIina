
/* RentalProduct.java */

package javaiina;

public class RentalProduct
{
    private long mProductId;
    private String mProductName;
    private long mCategoryId;
    private String mDescription;
    
    public long productId() { return this.mProductId; }
    public String productName() { return this.mProductName; }
    public long categoryId() { return this.mCategoryId; }
    public String description() { return this.mDescription; }
    
    public RentalProduct(
        long productId, String productName,
        long categoryId, String description)
    {
        this.mProductId = productId;
        this.mProductName = productName;
        this.mCategoryId = categoryId;
        this.mDescription = description;
    }
    
    @Override
    public String toString()
    {
        return String.format(
            "RentalProduct [Id: %s, ProductName: %s, " +
            "CategoryId: %s, Description: %s]",
            this.mProductId, this.mProductName,
            this.mCategoryId, this.mDescription);
    }
}
