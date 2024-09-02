using Dashdoc.API.Domain.Abstract;
using Dashdoc.API.Domain.Entities;
using Microsoft.EntityFrameworkCore;

namespace Dashdoc.API.Data.Repositories;

public class TestRepository: ITestRepository
{
    private readonly DashdocDbContext _dbContext;
    
    public TestRepository(DashdocDbContext dbContext)
    {
        _dbContext = dbContext;
    }
    
    // TODO - replace this test call with proper CRUD operations
    public async Task<List<Provider>> GetAll() => await _dbContext.Provider.ToListAsync();

}