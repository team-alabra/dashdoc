using Dashdoc.API.Domain.Abstract;
using Dashdoc.API.Domain.Entities;

namespace Dashdoc.API.Data.Repositories;

public class AgencyRepository: IAgencyRepository
{   
    private readonly DashdocDbContext _dbContext;

    public AgencyRepository(DashdocDbContext dbContext)
    {
        _dbContext = dbContext;
    }
    
    public Task<Agency> GetById(long agencyId)
    {
        throw new NotImplementedException();
    }

    public Task Create(Agency entityToCreate)
    {
        throw new NotImplementedException();
    }

    public Task Update(Agency entityToUpdate)
    {
        throw new NotImplementedException();
    }

    public Task Delete(Agency entityToDelete)
    {
        throw new NotImplementedException();
    }
}