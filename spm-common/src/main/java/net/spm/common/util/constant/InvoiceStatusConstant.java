package net.spm.common.util.constant;

/**
 * @author NhanNguyen
 */
public interface InvoiceStatusConstant {

	public static final int CREATED = 1;
	public static final String CREATED_STATUS_STR = "Mới tạo";
	
	public static final int PUBLISED = 2;
	public static final String PUBLISED_STATUS_STR = "Đã phát hành";
	
	public static final int DESTROYED = 3;
	public static final String DESTROYED_STATUS_STR = "Đã hủy";
	
	public static final int WAITING_FOR_REPLACED = 5;
	public static final String WAITING_FOR_REPLACED_STATUS_STR = "Chờ thay thế";
	
	public static final int REPLACE = 6;
	public static final String REPLACE_STATUS_STR = "Thay thế";
	
	public static final int WAITING_FOR_UPDATED = 7;
	public static final String WAITING_FOR_UPDATED_STATUS_STR = "Chờ điều chỉnh";
	
	public static final int UPDATE = 8;
	public static final String UPDATE_STATUS_STR = "Điều chỉnh";
	
	public static final int WAS_REPLACED = 9;
	public static final String WAS_REPLACED_STATUS_STR = "Bị thay thế";
	
	public static final int WAS_UPDATED = 10;
	public static final String WAS_UPDATED_STATUS_STR = "Bị điều chỉnh";
	
	public static final int NULL = 11;
	public static final String NULL_STATUS_STR = "Trống";
	
	public static final int NOT_USED = 12;
	public static final String NOT_USED_STATUS_STR = "Không sử dụng";
}
