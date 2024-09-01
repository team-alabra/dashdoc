using Dashdoc.API.Data.Repositories;
using Dashdoc.API.Data.Tests.MockData;
using Dashdoc.Tests.Common;

namespace Dashdoc.API.Data.Tests.Repositories;

public class AgencyRepositoryTests
{
    public AgencyRepositoryTests() { }
    
    // POST and GET
    [Fact]
    public async Task GetByIdAsync_Creates_And_ReturnsEntity()
    {
        // Arrange
        var factory = new TestDBContextFactory();
        var context = factory.CreateContext();
        
        var agencyRepository = new AgencyRepository(context); 
        
        // Act
        var fakeAgency = AgencyFactory.GetFaker().Generate(1).First(); // Generate returns an array even if you only generate one item, this is why I use first after to get the first element of the array.
        var agencyId = fakeAgency.Id;
        context.Agency.Add(fakeAgency); // This line saves our entity to the in-memory database
        context.SaveChanges();
        
        var result = await agencyRepository.GetByIdAsync(agencyId);

        // Assert
        Assert.NotNull(result);
        Assert.Equal(agencyId, result?.Id);
    }
    
    // PUT
    [Fact]
    public async Task UpdateAsync_ReturnsEntity()
    {
        // Arrange
        var factory = new TestDBContextFactory();
        var context = factory.CreateContext();
        
        var agencyRepository = new AgencyRepository(context); 
        
        // Act
        var fakeAgency = AgencyFactory.GetFaker().Generate(1).First(); // Generate returns an array even if you only generate one item, this is why I use first after to get the first element of the array.
        var agencyId = fakeAgency.Id;
        context.Agency.Add(fakeAgency); // This line saves our entity to the in-memory database
        context.SaveChanges();

        fakeAgency.Name = "Test Update 1";
        
        var result = await agencyRepository.UpdateAsync(fakeAgency);

        // Assert
        Assert.NotNull(result);
        Assert.Equal("Test Update 1", result?.Name);
    }
    
    // PUT
    [Fact]
    public async Task DeleteAsync_EntityCannotBeFound()
    {
        // Arrange
        var factory = new TestDBContextFactory();
        var context = factory.CreateContext();
        
        var agencyRepository = new AgencyRepository(context); 
        
        // Act
        var fakeAgency = AgencyFactory.GetFaker().Generate(1).First(); // Generate returns an array even if you only generate one item, this is why I use first after to get the first element of the array.
        var agencyId = fakeAgency.Id;
        context.Agency.Add(fakeAgency); // This line saves our entity to the in-memory database
        context.SaveChanges();
        
        var result = await agencyRepository.DeleteAsync(agencyId);
        var entity = await agencyRepository.GetByIdAsync(agencyId);
        
        // Assert
        Assert.True(result);
        Assert.Null(entity);
    }
}