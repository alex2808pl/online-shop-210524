package de.telran.onlineshop.repository;

import de.telran.onlineshop.entity.CategoriesEntity;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CategoriesRepositoryTest {

    @Autowired
    private CategoriesRepository categoriesRepositoryTest;

    private static final String NAME_TEST = "Test";
    private static CategoriesEntity categoriesEntityNewTest;

    @BeforeAll
    static void setStart() {
        categoriesEntityNewTest = new CategoriesEntity(null, NAME_TEST);
        System.out.println("Выполняется setStart(единоразово, перед запуском всех тестов)!");
    }

    @AfterAll
    static void setEnd() {
        System.out.println("Выполняется setEnd(единоразово, после выполнения всех тестов!");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Выполняется setUp (перед каждым тестом)!");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Выполняется tearDown (после каждого тестом)!");
    }

    @Test
    void findAllTest() {
        List<CategoriesEntity> categoriesTest = categoriesRepositoryTest.findAll();
        assertNotNull(categoriesTest);
    }

    @Test
    void insertTest() {
        String nameExpected = NAME_TEST;
        CategoriesEntity categoriesTest = categoriesEntityNewTest;

        CategoriesEntity categoriesActual = categoriesRepositoryTest.save(categoriesTest);

        assertNotNull(categoriesActual);
        assertTrue(categoriesActual.getCategoryId()!=null);
        assertEquals(nameExpected, categoriesActual.getName());
    }

    @Test
    void updateTest() {
        // when
        CategoriesEntity categoriesExpected = generateTestData();

        // test
        String nameExpected = "NewTest";
        // if(true) throw new NullPointerException("Imitation");
        categoriesExpected.setName(nameExpected);
        CategoriesEntity categoriesActual = categoriesRepositoryTest.save(categoriesExpected);

        //assert
        assertNotNull(categoriesActual);
        assertEquals(nameExpected, categoriesActual.getName());

    }

    @Test
    void findByNameTest() {
        //when
        CategoriesEntity categoriesExpected = generateTestData();

        // run
        String nameExpected = categoriesExpected.getName();
        CategoriesEntity categoriesActual = categoriesRepositoryTest.findByName(nameExpected);

        //assert
        assertNotNull(categoriesActual);
        assertEquals(nameExpected, categoriesActual.getName());

        // подчищаем ?
    }

    @Test
    void deleteTest() {
        //when
        CategoriesEntity categoriesExpected = generateTestData();

        //run

        // 1 вариант удаление
        Long idTest = categoriesExpected.getCategoryId();
        categoriesRepositoryTest.deleteById(idTest);

        // 2 вариант удаление
        categoriesRepositoryTest.delete(categoriesExpected);

        //проверка
        // 1 вариант
        Optional<CategoriesEntity> categoriesActualOptional = categoriesRepositoryTest.findById(idTest);
        assertFalse(categoriesActualOptional.isPresent());

        // 2 вариант
        CategoriesEntity categoriesActual = categoriesRepositoryTest.findById(idTest).orElse(null);
        assertNull(categoriesActual);
    }

    @Test
    void findByNameNativeTest() {
        //when
        CategoriesEntity categoriesExpected = generateTestData();

        // run
        String nameExpected = categoriesExpected.getName();
        CategoriesEntity categoriesActual = categoriesRepositoryTest.findByNameNative(nameExpected);

        //assert
        assertNotNull(categoriesActual);
        assertEquals(nameExpected, categoriesActual.getName());

        // подчищаем ?
    }

    private CategoriesEntity generateTestData() {
        CategoriesEntity categoriesInsert = categoriesRepositoryTest.save(categoriesEntityNewTest);
        assertNotNull(categoriesInsert);
        assertTrue(categoriesInsert.getCategoryId()!=null);
        assertEquals(NAME_TEST, categoriesInsert.getName());
        return categoriesInsert;
    }
}