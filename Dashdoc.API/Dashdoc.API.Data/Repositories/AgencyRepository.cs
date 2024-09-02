using Dashdoc.API.Domain.Abstract;
using Dashdoc.API.Domain.Entities;
using Microsoft.EntityFrameworkCore;

namespace Dashdoc.API.Data.Repositories;

public class AgencyRepository: IAgencyRepository
{   
    private readonly DashdocDbContext _dbContext;

    public AgencyRepository(DashdocDbContext dbContext)
    {
        _dbContext = dbContext;
    }

    public async Task<Agency?> GetByIdAsync(long agencyId) => await _dbContext.Agency.FirstOrDefaultAsync(e => e.Id == agencyId);
    
    public async Task<Agency> CreateAsync(Agency entityToCreate)
    {
        ArgumentNullException.ThrowIfNull(entityToCreate);

        entityToCreate.CreatedOn = DateTime.UtcNow;
        entityToCreate.LastUpdated = null;
        
        var addResult = await _dbContext.Agency.AddAsync(entityToCreate);

        await _dbContext.SaveChangesAsync();

        return addResult.Entity;
    }

    public async Task<Agency> UpdateAsync(Agency entityToUpdate)
    {
        ArgumentNullException.ThrowIfNull(entityToUpdate);

        entityToUpdate.LastUpdated = DateTime.UtcNow;

        var updateResult = _dbContext.Agency.Update(entityToUpdate);
        await _dbContext.SaveChangesAsync();

        return updateResult.Entity;
    }

    public async Task<bool> DeleteAsync(long agencyId)
    {
        var entityToDelete = await _dbContext.Agency.FirstOrDefaultAsync(e => e.Id == agencyId);

        if (entityToDelete == null) return false;
        
        _dbContext.Agency.Remove(entityToDelete);
        await _dbContext.SaveChangesAsync();
            
        return true;

    }
}