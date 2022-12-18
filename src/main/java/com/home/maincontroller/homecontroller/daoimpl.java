package com.home.maincontroller.homecontroller;

//import java.util.List;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.stereotype.Repository;





@Repository
public class daoimpl implements daoclass {

	@Autowired
	private SessionFactory ss;
/*	
	private Session getsession()
	{
		return ss.getCurrentSession();
		
	}*/
	@Override
	@Transactional
	public void add(model m) {
		// TODO Auto-generated method stub
		Session session=ss.getCurrentSession();//(Session) ses((HibernateEntityManagerFactory) ss);
		session.save(m);
		System.out.println("successfully inserted");
		//return m;
		
	}
	@Override
	@Transactional
	public List<model> show() {

		Session session=ss.getCurrentSession();
		List<model> list=session.createQuery("from model").list();
		return list;
		//return null;
	}
	@Override
	@Transactional
	public void delete(int id) {
		// TODO Auto-generated method stub
		Session session=ss.getCurrentSession();
		model m=(model)session.load(model.class, id);
		session.delete(m);
		System.out.println("Sucessfully delete");
	}
	@Override
	@Transactional
	public void update(model m) {
		// TODO Auto-generated method stub
		Session session=ss.getCurrentSession();
		session.update(m);
		System.out.println("update");
	}
	@Override
	@Transactional
	public List<model> pdf(int id) {
		
		Session session=ss.getCurrentSession();
		model m=(model)session.load(model.class, id);
		List<model> list=session.createQuery("from model where id=:id").setInteger("id",id).list();	
		return list;
		
		//return null;
	}
	@Override
	@Transactional
	public model edit(int id) {
		// TODO Auto-generated method stub
		Session session=ss.getCurrentSession();
		model list=(model) session.createQuery("from model where id=:id").setParameter("id",id).uniqueResult();
		return list;
	}


}



