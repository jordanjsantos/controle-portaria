/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controle.portaria.database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;


public class GenericDao<T>{
	
	private final Session session;
       
    public GenericDao(){
		this.session = HibernateConection.getSessao(); 
	}
	
    public void fecharSessao(){
    	this.session.close();
    }
    
	public void salva(T objeto) {		
		Transaction tx = this.session.beginTransaction();
		try{
			this.session.saveOrUpdate(objeto);
	        tx.commit();  
		} catch(HibernateException he){
		    he.printStackTrace(); 
		    tx.rollback();
		}
        this.session.close();
	}
	
	public void altera(T objeto){
		Transaction tx = this.session.beginTransaction();
		try{
			this.session.update(objeto);
			tx.commit();
		} catch(HibernateException he){
		    he.printStackTrace(); 
		    tx.rollback();
		}
		this.session.close();
		
	}
	
	public void excluir(T objeto){
	    
		Transaction tx = session.beginTransaction();
	    
	    try{ 
	    	session.delete(objeto);
	    }catch(HibernateException he){
	    	he.printStackTrace();               
	        tx.rollback();
	        
	    }
        tx.commit();
        this.session.close();
	}
	
	public void excluir(Collection<T> objetos){
	    
		Transaction tx = session.beginTransaction();
	    
	    try { 
	    	for(T objeto : objetos){
	    		session.delete(objeto);
	    	}
	    	
	    } catch(HibernateException he){
	    	he.printStackTrace();               
	        tx.rollback();
	        
	    }
        tx.commit();
        this.session.close();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<T> listaTudo(Class<T> arg0){
		return this.session.createCriteria(arg0).list();
	}
	
	@SuppressWarnings("unchecked")    
    public List<T> listaTudo(String hql){
		
		List<T> lista = new ArrayList<T>();
        try{
             lista = (List<T>) this.session.createQuery(hql).list();
        }catch(HibernateException he){
        	he.printStackTrace();               
        }
        this.session.close();       
        
        return lista;
    }
	
	// Busca por criterio, args necessarios name e values columm dos models, alem da class model

	@SuppressWarnings("unchecked")
	public List<T> busca(String nameColumm, String valueColumm, Class<T> arg0) {
		if(valueColumm.toString().equals(valueColumm)){			
			return this.session.createCriteria(arg0)
				.add(Restrictions.like(nameColumm, "%"+valueColumm+"%")).list();	
		}else{
			return this.session.createCriteria(arg0).add(Restrictions.eq(nameColumm, valueColumm)).list();
		}
	}
	
	public void recarrega(T objeto) {		
		this.session.refresh(objeto);		
	}
	
	public T carrega(Integer id, Class<T> arg0){
		T objeto=null;
		
		try{			
			objeto = (T) this.session.get(arg0, id);
			
		}catch(HibernateException he){			
			he.printStackTrace();			            
		}
		
		this.session.close();
		return objeto;
	}
	
	@SuppressWarnings("unchecked")
	public T carregarUltimoRegistro(Class<T> arg0, 
					String nmAtributoRestricao, 
					Integer vlCodRestricao, 
					String nmAtributoOrder) throws Exception {  
        
		Criteria criteria = session.createCriteria( arg0 )
				.add(Restrictions.eq(nmAtributoRestricao, vlCodRestricao));  
		
        criteria.addOrder( Order.desc( nmAtributoOrder ) );  
        return (T) criteria.setMaxResults( 1 ).uniqueResult();   
    }  

	public void adicionarOrAlterar(T objeto) {
            
		Transaction tx = this.session.beginTransaction();
            
		try{			    
			this.session.saveOrUpdate(objeto);	
		    tx.commit();		    
		    
		}catch(HibernateException he){			
			he.printStackTrace();
		    tx.rollback();		    
		}
		this.session.close();
            	
	}	
	
//	public Boolean naoExisteUsuario(Usuario usuario){
//		
//		return 	this.session.createCriteria(Usuario.class)
//				.add(Restrictions.eq("nmUsuario", usuario.getNmUsuario()))
//				.add(Restrictions.eq("pwUsuario", usuario.getPwUsuario()))
//				.uniqueResult() == null;				
//	}
	
    
}

