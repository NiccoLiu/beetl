package @{crud.properties.base_package}.@{crud.properties.model}.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * @{crud.table.remarks} 实体类
 * @author generator
 */
@TableName(value = "@{crud.table.tableName}")
public class @{crud.table.className} extends Model<@{crud.table.className}> {

	private static final long serialVersionUID = 1L;

    // columns START
	# for(column in crud.table.columns){ #

		# if(column.pk) { #
	@TableId(value = "@{strutils.toLowerCase(column.columnName)}",type=IdType.ID_WORKER)
		# } else { #
	/**
	 * @{column.remarks}
	 */
			# if(strutils.toLowerCase(column.columnName)!=strutils.toLowerCaseFirst(column.columnJavaName) ) {#
	@TableField(value = "@{strutils.toLowerCase(column.columnName)}")
			# } #
		# } #
	private @{column.javaType} @{strutils.toLowerCaseFirst(column.columnJavaName)}; 
    # } #
	// columns END

	# for(column in crud.table.columns){ #
	# if(column.pk) { #
	@Override
	protected Serializable pkVal() {
		return @{strutils.toLowerCase(column.columnName)};
	}
	# } #
	# } #

    # for(column in crud.table.columns){ #
	public @{column.javaType} get@{strutils.toUpperCaseFirst(column.columnJavaName)}() {
		return @{strutils.toLowerCaseFirst(column.columnJavaName)};
	}

    public void set@{strutils.toUpperCaseFirst(column.columnJavaName)}(@{column.javaType} @{strutils.toLowerCaseFirst(column.columnJavaName)}) {
    	this.@{strutils.toLowerCaseFirst(column.columnJavaName)} = @{strutils.toLowerCaseFirst(column.columnJavaName)};
    }
	# } #
	
	@Override
	public String toString() {
		String log = ""; 
	# for(column in crud.table.columns){ #
		log += "[@{strutils.toLowerCaseFirst(column.columnJavaName)}:" + get@{strutils.toUpperCaseFirst(column.columnJavaName)}() + "]";
	# } #
		return log;
	}
}