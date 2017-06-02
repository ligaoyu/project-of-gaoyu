package com.asset.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.asset.dao.mapper.impl.SqlProvider;
import com.asset.model.Asset;
import com.asset.vo.SelAssetVo;

/**
 * mapper类使用接口形式。
 * 
 * @author Administrator
 *
 */
public interface AssetMapper {

	/**
	 * 查询使用select注解
	 * 
	 * @return
	 */
	@Select("select c.*,d.dname from asset c left join "
			+ "(select a.asset_id,(select name from fixed_asset.usr_dept where id=a.dept_id) as dname from fixed_asset.asset_dept a join "
			+ "(SELECT asset_id,max(creation) as last_time FROM fixed_asset.asset_dept group by asset_id) b "
			+ "on a.asset_id=b.asset_id where creation=last_time and type=0) d on c.id=d.asset_id where removed = 0 and level = 2 ")
	public List<Asset> queryAllAsset();

	/**
	 * 查询第二层级数据
	 * 
	 * @return
	 */
	@SelectProvider(type = SqlProvider.class, method = "queryAllAssetByAssetVo")
	public List<Asset> queryAllAssetByAssetVo(SelAssetVo vo);

	/**
	 * 查询第一层级数据
	 * 
	 * @return
	 */
	@Select("select * from asset where removed = 0 and level=1")
	public List<Asset> queryAllParent();

	/**
	 * 查询第一层级数据的name和id
	 * 
	 * @return
	 */
	@Select("select id,name from asset where removed = 0 and level=1")
	public List<Asset> queryAllParentIdAndName();

	/**
	 * 根据parent的名称查询单条数据
	 * 
	 * @return
	 */
	@Select("select * from asset where removed = 0 and level=1 and name = #{name } ")
	public Asset queryParentByName(String name);

	/**
	 * 插入数据
	 * 
	 * @param asset
	 * @return
	 */
	@Insert("insert into asset(name,creation,last_modified,asset_no,produce_date,level,parent_id,price,owner_dept,model,brand,remark) "
			+ "values (#{name },now(),now(),#{assetNo},#{produceDate },2,#{parentId },#{price },8,#{model },#{brand },#{remark })")
	public int save(Asset asset);

	@Update("update asset set type=1 where id = #{id } ")
	public int lend(String id);

	@Update("update asset set type=0 where id = #{id } ")
	public int getBack(String id);

	@Select("select c.value as percent,d.name from (" + "select a.parent_id,IFNULL(in_use/total,0) as value from "
			+ "(select parent_id,count(1) as total from fixed_asset.asset where level=2 group by parent_id) a left join "
			+ "(SELECT parent_id,type,count(*) as in_use FROM fixed_asset.asset where level=2 and type=1 group by parent_id,type) b "
			+ " on a.parent_id=b.parent_id ) c join fixed_asset.asset d on c.parent_id=d.id order by percent desc")
	public List<Map<String, String>> getPercent();

	@Select("select count(1) as value ,b.name from fixed_asset.asset a left join fixed_asset.asset b on a.parent_id=b.id where a.level=2 group by a.parent_id ")
	public List<Map<String, String>> getPieData();

	@Select("select (select name from fixed_asset.asset where id=d.parent_id) as parent_name,dname,count(*) as num "
			+ " from (select a.asset_id,(select name from fixed_asset.usr_dept where id=a.dept_id) as dname from fixed_asset.asset_dept a join "
			+ "(SELECT asset_id,max(creation) as last_time FROM fixed_asset.asset_dept group by asset_id) b "
			+ "on a.asset_id=b.asset_id where creation=last_time and type=0) c join fixed_asset.asset d on c.asset_id = d.id group by parent_id,dname")
	public List<Map<String, String>> getDeptUsingInfo();

}
