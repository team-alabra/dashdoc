using Dashdoc.API.Data.Repositories;
using Dashdoc.API.Data.Tests.MockData;
using Dashdoc.Tests.Common;

namespace Dashdoc.API.Data.Tests.Repositories;

public class AgencyRepositoryTests
{
    public AgencyRepositoryTests() { }

    [Fact]
    public async Task GetById_ReturnsEntity()
    {
        // Arrange
        var factory = new TestDBContextFactory<DashdocDbContext>();
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
}