package com.bishe.home.dao;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bishe.home.entity.QueryResult;
import com.bishe.home.util.GenericsUtils;

 
public abstract class DaoSupport<T> extends HibernateDaoSupport implements DAO<T> {
	
	@Autowired  
    public void setSessionFactoryOverride(SessionFactory sessionFactory)  
    {  
  
        super.setSessionFactory(sessionFactory);  
    }  
	
	
	/* 实体类 */
	protected Class<T> entityClass = GenericsUtils.getSuperClassGenricType(getClass());
	public QueryResult<T> getScrollData(){
		return getScrollData(-1, -1, null, null, null);
	}
	
	public QueryResult<T> getScrollData(int firstResult, int maxResult){
		return getScrollData(firstResult, maxResult, null, null, null);
	}
	
	public QueryResult<T> getScrollData(int firstResult, int maxResult, LinkedHashMap<String, String> orderby){
		return getScrollData(firstResult, maxResult, null, null, orderby);
	}
	
	public QueryResult<T> getScrollData(int firstResult, int maxResult, String where, Object[] params){
		return getScrollData(firstResult, maxResult, where, params, null);
	}
	
	public QueryResult<T> getScrollData(int firstResult, int maxResult, String where, Object[] params, LinkedHashMap<String, String> orderby){
		String entityName = getEntityName(entityClass);
		String whereql = where!=null && !"".equals(where.trim()) ? " where "+ where : "" ;
		Session session=getSession();
		Query query =session.createQuery("select o from "+ entityName +" o" + whereql + buildOrderby(orderby));
		if(firstResult!=-1 && maxResult!=-1) 
			query.setFirstResult(firstResult).setMaxResults(maxResult);
		setQueryParameter(query, params);
		
		QueryResult<T> qr = new QueryResult<T>();
		//qr.setResultlist(query.getResultList());
		Query queryCount =session.createQuery("select count(o) from "+ entityName +" o"+ whereql);
		setQueryParameter(queryCount, params);
		long count=(Long)queryCount.uniqueResult();
		qr.setTotal(count);
		qr.setRows(query.list());
		return qr;
	}
	/**
	 * 设置查询参数
	 * @param query 查询对象
	 * @param params 参数值
	 */
	public static void setQueryParameter(Query query, Object[] params){
		if(params!=null){
			for(int i = 0; i < params.length ; i++){
				query.setParameter(i, params[i]);
			}
		}
	}
	
	/**
	 * 构建排序语句
	 * @param orderby 排序属性与asc/desc, Key为属性,Value为asc/desc
	 * @return
	 */
	public static String buildOrderby(LinkedHashMap<String, String> orderby){
		StringBuilder sb = new StringBuilder();
		if(orderby!=null && !orderby.isEmpty()){
			sb.append(" order by ");
			for(Map.Entry<String, String> entry : orderby.entrySet()){
				sb.append("o.").append(entry.getKey()).append(" ").append(entry.getValue()).append(',');
			}
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}
	public void delete(Serializable entityid) {
		Transaction beginTransaction = getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
		T entity=find(entityid);
		getHibernateTemplate().delete(entity);
		//beginTransaction.commit();
	}

	@SuppressWarnings("unchecked")
	public T find(Serializable entityid) {
		return (T)getHibernateTemplate().get(entityClass, entityid);
	}
	
	public long getCount() {
		return (Long)(getHibernateTemplate().find("select count(o) from " + getEntityName(this.entityClass) + " o").get(0));
	}
	/**
	 * 获取实体名称
	 * @return
	 */
	protected static <E> String getEntityName(Class<E> entityClass){
		String entityName = entityClass.getSimpleName();
		return entityName;
	}

	public void save(T entity) {
		getHibernateTemplate().save(entity);
	}

	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}

}
