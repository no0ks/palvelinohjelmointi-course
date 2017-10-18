package fi.nooks.aanilevyt.batch;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import fi.nooks.aanilevyt.bean.Aanilevy;
import fi.nooks.aanilevyt.dao.AanilevyDao;

public class AanilevyOhjelma {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		AanilevyDao levyDao = (AanilevyDao)context.getBean("daoClass");
		
		System.out.println("-------------------");
		System.out.println("LISTATAAN KAIKKI ÄÄNILEVYT");
		System.out.println("-------------------");
		System.out.println();
		
		List<Aanilevy> levyt = levyDao.listaaLevyt();
		for (Aanilevy levy : levyt) {
			System.out.println(levy.getLevyId());
			System.out.println(levy.getLevyNimi());
			System.out.println(levy.getArtisti());
			System.out.println(levy.getJulkaisuvuosi());
			System.out.println();
		}
		System.out.println("-------------------");
		System.out.println("LOPPU");
		context.close();
	}

}
