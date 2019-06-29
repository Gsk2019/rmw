package org.cs.rmw.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.cs.core.service.impl.BaseService;
import org.cs.rmw.dao.IPriceTrendDao;
import org.cs.rmw.model.Category;
import org.cs.rmw.model.PriceTrend;
import org.cs.rmw.service.ICategoryService;
import org.cs.rmw.service.IInfoService;
import org.cs.rmw.service.IPriceTrendService;
import org.cs.util.Pager;
import org.cs.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.*;

@Service("priceTrendService")
public class PriceTrendService extends BaseService<PriceTrend> implements IPriceTrendService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IPriceTrendDao priceTrendDao;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IInfoService infoService;

	/**
	 *  后台管理编辑
	 * @return
	 * @return: boolean
	 */
	@Override
	@Transactional(readOnly = false)
	public JSONObject updatePriceTrend_admin(PriceTrend priceTrend){

		PriceTrend pt = priceTrendDao.findById(priceTrend.getId());
		if(pt != null){
			pt.setAvePrice(priceTrend.getAvePrice());
            pt.setMinPrice(priceTrend.getMinPrice());
			pt.setStatus(priceTrend.getStatus());
			this.priceTrendDao.update(pt);
		}
		return getCode("200", "操作成功");
	}

	/**
	 * 后台列表查询
	 * @param page
	 * @param map
	 * @return
	 */
	@Override
	public Pager getPriceTrendBypages_admin(Pager page, Map<String, String> map){
		Pager pager = this.priceTrendDao.queryPriceTrendPages_admin(page,map);
		return pager;
	}

	/**
	 * 后台管理删除
	 * @return
	 */
	@Transactional(readOnly=false)
	public JSONObject deletePriceTrend_admin(Integer id){
		PriceTrend priceTrend = this.priceTrendDao.findById(id);
		priceTrend.setIsDelete(true);
		this.priceTrendDao.update(priceTrend);
		return getCode("200", "操作成功");
	}

    /**
     * 计算当日一级品类的平均价格
     * @return
     */
    @Transactional(readOnly=false)
    public void doPriceTrend_admin(){
        List<Category> clist=categoryService.getTopCategoryList();
        for (int i = 0; i < clist.size(); i++) {
            Category category =  clist.get(i);
                PriceTrend tp=new PriceTrend();
                tp.setStatus(1);
                tp.setCategoryId(category.getId());
                tp.setCategoryName(category.getCategoryName());
                tp.setCreateDate(new Date());
                tp.setModifyDate(new Date());
                tp.setIsDelete(false);
                tp.setPriceDate(new Date());
                String ap=infoService.getAveragePrice_admin(category.getId());
                String vp=getYesterdayAvePrice_admin(category.getId());
                if (StringUtil.isNotBlank(ap)){
                    BigDecimal bd=new BigDecimal(ap).setScale(2, BigDecimal.ROUND_HALF_UP);
                    tp.setAvePrice(bd);
                }else {
                    if (StringUtil.isNotBlank(vp)){
                        tp.setAvePrice(new BigDecimal(vp).setScale(2, BigDecimal.ROUND_HALF_UP));
                    }else {
                        tp.setAvePrice(new BigDecimal("2018.00").setScale(2, BigDecimal.ROUND_HALF_UP));
                    }
                }

                String apMin=infoService.getMinPrice_admin(category.getId());
                String vpMin=getYesterdayMinPrice_admin(category.getId());

                if (StringUtil.isNotBlank(apMin)){
                    BigDecimal bdMin=new BigDecimal(apMin).setScale(2, BigDecimal.ROUND_HALF_UP);
                    tp.setMinPrice(bdMin);
                }else {
                    if (StringUtil.isNotBlank(vpMin)){
                        tp.setMinPrice(new BigDecimal(vpMin).setScale(2, BigDecimal.ROUND_HALF_UP));
                    }else {
                        tp.setMinPrice(new BigDecimal("2018.00").setScale(2, BigDecimal.ROUND_HALF_UP));
                    }
                }
                priceTrendDao.insert(tp);
        }
    }

    /**
     * 获取某个品类前一天的平均价格
     * @return
     */
    @Transactional(readOnly=false)
    public String getYesterdayAvePrice_admin(Integer id){
        List<Map<String,Object>>  tlist= this.priceTrendDao.findBySql("SELECT p.ave_price avePrice FROM rmw_price_trend p WHERE p.category_id ="+id+" AND p.price_date = date_sub(curdate(),interval 1 day)");
        if (tlist!=null&&tlist.size()>0){
            Map<String,Object> m=tlist.get(0);
            return m.get("avePrice").toString();
        }else {
            return null;
        }
    }

    /**
     * 获取某个品类前一天的最低价格
     * @return
     */
    @Transactional(readOnly=false)
    public String getYesterdayMinPrice_admin(Integer id){
        List<Map<String,Object>>  tlist= this.priceTrendDao.findBySql("SELECT p.min_price minPrice FROM rmw_price_trend p WHERE p.category_id ="+id+" AND p.price_date = date_sub(curdate(),interval 1 day)");
        if (tlist!=null&&tlist.size()>0){
            Map<String,Object> m=tlist.get(0);
            return m.get("minPrice").toString();
        }else {
            return null;
        }
    }

//    =================================价格走势============================================
    /**
     * 前台列表查询
     * @param page
     * @param map
     * @return
     */
    @Override
    public Pager getPriceTrends(Pager page, Map<String, String> map){
        Pager pager = this.priceTrendDao.getPriceTrends(page,map);
        return pager;
    }

    private String[] colors = new String[]{"#f44336", "#e91e63", "#9c27b0", "#673ab7", "#3f51b5", "#2196f3", "#03a9f4", "#00bcd4", "#009688", "#4caf50", "#8bc34a", "#cddc39", "#ffeb3b", "#ffc107", "#ff9800", "#ff5722", "#795548", "#9e9e9e", "#607d8b"};

    public Map<String, Object> getBrokenTrend(Map<String, String> map){
        Map<String, Object> bak = new HashMap<>();

        Map<String, Object> dmap = new HashMap<>();
        //横向坐标
        List<String> slist = new ArrayList<>();
        //所有要展示的分类
        List<String> clist = new ArrayList<>();
        //所有要展示的分类名字
        List<String> clist1 = new ArrayList<>();

        //折线图
        List<Map<String, Object>> mlist = new ArrayList<>();

        List<Map<String, Object>> allTrend = this.priceTrendDao.getBrokenTrend(map);

        for(Map<String, Object> atm : allTrend){

            Map<String, Object> somap = new HashMap<>();
            dmap.put(atm.get("category_id").toString(), null);

            //所有要展示的分类
            boolean cboo = true;
            for(String str: clist){
                if(str.equals(atm.get("category_id").toString())){
                    cboo = false;
                }
            }
            if(cboo){
                clist.add(atm.get("category_id").toString());
                /*somap.put("category_name", atm.get("category_name").toString());
                mlist.add(somap);*/
            }
            //所有要展示的分类
            boolean cboo1 = true;
            for(String str: clist1){
                if(str.equals(atm.get("category_name").toString())){
                    cboo1 = false;
                }
            }
            if(cboo1){
                clist1.add(atm.get("category_name").toString());
                /*somap.put("category_name", atm.get("category_name").toString());
                mlist.add(somap);*/
            }

            //横向坐标
            boolean strboo = true;
            for(String str: slist){
                if(str.equals(atm.get("price_data").toString())){
                    strboo = false;
                }
            }
            if(strboo){
                slist.add(atm.get("price_data").toString());
            }
        }

        /*{
                name:'液态奶',
                type:'line',
                color:'#1e9fff',
                data:[320, 332, 301, 334, 390, 330, 320]
        }*/
        for(int i=0;i<clist.size();i++){
            String id = clist.get(i);
            //单个分类
            Map<String, Object> mm = new HashMap<>();
            Map<String, Object> mmm = new HashMap<>();
            List<Float> plist = new ArrayList<>();
            List<Float> mplist = new ArrayList<>();
            for(String d:slist){
                boolean dbo = true;
                for(Map<String, Object> atm : allTrend){
                    if(id.equals(atm.get("category_id").toString()) && d.equals(atm.get("price_data").toString()) && dbo){
                        plist.add(Float.valueOf(atm.get("ave_price").toString()));
                        mplist.add(Float.valueOf(atm.get("min_price").toString()));
                        dbo = false;
                        break;
                    }
                }
            }
            mm.put("name", clist1.get(i)+"（均值）");
            mm.put("type", "line");
            mm.put("color", "#f44336");
            mm.put("data", plist.toArray());
            mmm.put("name", clist1.get(i)+"（最低值）");
            mmm.put("type", "line");
            mmm.put("color", "#ffeb3b");
            mmm.put("data", mplist.toArray());
            mlist.add(mm);
            mlist.add(mmm);
        }
        bak.put("dline", slist.toArray());
        bak.put("aline", mlist);

        return bak;
    }
}
