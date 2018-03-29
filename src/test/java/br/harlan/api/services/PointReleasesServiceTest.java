package br.harlan.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.harlan.api.entities.PointReleasesEntity;
import br.harlan.api.repositories.PointReleasesRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PointReleasesServiceTest {

	@MockBean
	private PointReleasesRepository pointReleasesRepository;
	@Autowired
	PointReleasesService pointReleasesService;

	@Before
	public void setUp() throws Exception {
		BDDMockito.given(pointReleasesRepository.save(Mockito.any(PointReleasesEntity.class)))
				.willReturn(new PointReleasesEntity());
//		BDDMockito.given(pointReleasesRepository.findByEmployeesEntityId(Mockito.anyLong()))
//				.willReturn(new ArrayList<PointReleasesEntity>());
		BDDMockito
				.given(pointReleasesRepository.findByEmployeesId(Mockito.anyLong(),
						Mockito.any(PageRequest.class)))
				.willReturn(new PageImpl<PointReleasesEntity>(new ArrayList<PointReleasesEntity>()));
		BDDMockito.given(pointReleasesRepository.findOne(Mockito.anyLong())).willReturn(new PointReleasesEntity());
	}
	
	@Test
	public void testPageFindByEmployeesId() {
		Page<PointReleasesEntity> pointReleases = pointReleasesService.findByEmployeeId(1L, new PageRequest(1, 10));
		assertNotNull(pointReleases);
	}
	
	@Test
	public void testFindById() {
		Optional<PointReleasesEntity> pointRelease = pointReleasesService.findById(1L);
		assertTrue(pointRelease.isPresent());
	}
	
	@Test
	public void testCreatePointRelease() {
		PointReleasesEntity pointRelease = pointReleasesRepository.save(new PointReleasesEntity());
		assertNotNull(pointRelease);
	}
}