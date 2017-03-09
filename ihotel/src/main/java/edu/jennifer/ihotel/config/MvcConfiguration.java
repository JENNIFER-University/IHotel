package edu.jennifer.ihotel.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import edu.jennifer.ihotel.dao.GuestDAO;
import edu.jennifer.ihotel.dao.GuestDAOImpl;
import edu.jennifer.ihotel.dao.HotelInfoDAO;
import edu.jennifer.ihotel.dao.HotelInfoDAOImpl;
import edu.jennifer.ihotel.dao.ReservationDAO;
import edu.jennifer.ihotel.dao.ReservationDAOImpl;
import edu.jennifer.ihotel.dao.RoomDAO;
import edu.jennifer.ihotel.dao.RoomDAOImpl;
import edu.jennifer.ihotel.dao.UserDAO;
import edu.jennifer.ihotel.dao.UserDAOImpl;

@Configuration
@ComponentScan(basePackages="edu.jennifer.ihotel")
@EnableWebMvc
@EnableScheduling
public class MvcConfiguration extends WebMvcConfigurerAdapter{

	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Bean
	public DataSource getDataSource(){
		DataSource dataSource = null;
		JndiTemplate jndi = new JndiTemplate();
		try{
			String jndiName = "java:/comp/env/jdbc/ihotelDS";
            dataSource = (DataSource) jndi.lookup(jndiName);
		}catch(NamingException ex){
			System.out.println("=============================");
			System.out.println("JNDI ["+jndi+"] Lookup failed");
		}
		return dataSource;
	}
	
	
	@Bean
	public HotelInfoDAO getHotelInfoDAO(){
		return new HotelInfoDAOImpl(getDataSource());
	}
	
	@Bean
	public GuestDAO getGuestDAO(){
		return new GuestDAOImpl(getDataSource());
	}
	
	@Bean
	public ReservationDAO getReservationDAO(){
		return new ReservationDAOImpl(getDataSource());
	}
	
	@Bean
	public RoomDAO geRoomDAO(){
		return new RoomDAOImpl(getDataSource());
	}
	
	@Bean
	public UserDAO getUserDAO(){
		return new UserDAOImpl(getDataSource());
	}

}
